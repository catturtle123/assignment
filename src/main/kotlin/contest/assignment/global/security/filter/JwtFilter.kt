package contest.assignment.global.security.filter

import contest.assignment.global.security.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.Authentication
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val excludePath: Array<String>
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = jwtUtil.resolveToken(request)

        if (accessToken == null) {
            filterChain.doFilter(request, response)
            return
        }

        if (jwtUtil.isTokenValid(accessToken)) {
            val authentication: Authentication = jwtUtil.getAuthentication(accessToken)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        return excludePath.contains(path)
    }
}