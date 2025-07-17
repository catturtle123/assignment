package contest.assignment.global.security.util


import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.code.GeneralErrorCode.AUTH_EXPIRED_TOKEN
import contest.assignment.global.apiPayload.exception.AuthException
import contest.assignment.global.security.principle.PrincipalDetails
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil(
    @Value("\${spring.jwt.secret}") secretKey: String,
    @Value("\${spring.jwt.access-token-validity}") accessTokenValiditySec: Long
) {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        private val log = LoggerFactory.getLogger(JwtUtil::class.java)
    }

    val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
    val accessTokenValiditySec: Long = accessTokenValiditySec

    fun createAccessToken(principalDetails: PrincipalDetails): String {
        return createToken(principalDetails, accessTokenValiditySec)
    }

    private fun createToken(principalDetails: PrincipalDetails, validitySeconds: Long): String {
        val issuedAt = Instant.now()
        val expirationTime = issuedAt.plusSeconds(validitySeconds)

        return Jwts.builder()
            .setSubject(principalDetails.getUserId().toString())
            .claim("role", principalDetails.authorities.joinToString(",") { it.authority })
            .setIssuedAt(Date.from(issuedAt))
            .setExpiration(Date.from(expirationTime))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getUserId(token: String): String {
        return getClaims(token).body.subject
    }

    fun getExpiration(token: String): Long {
        return getClaims(token).body.expiration.time
    }

    fun isTokenValid(token: String): Boolean {
        try {
            val claims = getClaims(token)
            return claims.body.expiration.after(Date())
        } catch (e: ExpiredJwtException) {
            throw AuthException(AUTH_EXPIRED_TOKEN)
        } catch (e: UnsupportedJwtException) {
            throw AuthException(GeneralErrorCode.AUTH_UNSUPPORTED_TOKEN)
        } catch (e: MalformedJwtException) {
            throw AuthException(GeneralErrorCode.AUTH_MALFORMED_TOKEN)
        } catch (e: io.jsonwebtoken.security.SignatureException) {
            log.error("JWT signature validation failed: ${e.message}")
            throw AuthException(GeneralErrorCode.AUTH_SIGNATURE_INVALID)
        } catch (e: SecurityException) {
            throw AuthException(GeneralErrorCode.AUTH_SIGNATURE_INVALID)
        } catch (e: IllegalArgumentException) {
            throw AuthException(GeneralErrorCode.AUTH_TOKEN_EMPTY)
        } catch (e: JwtException) {
            log.error("JWT processing error: ${e.message}", e)
            throw AuthException(GeneralErrorCode.AUTH_INVALID_TOKEN)
        } catch (e: Exception) {
            log.error("Unexpected JWT validation error: ${e.message}", e)
            throw AuthException(GeneralErrorCode.AUTH_TOKEN_PROCESSING_ERROR)
        }
    }

    private fun getClaims(token: String): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token).body
        val authorities: Collection<GrantedAuthority> = claims["role"]
            .toString()
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }
}
