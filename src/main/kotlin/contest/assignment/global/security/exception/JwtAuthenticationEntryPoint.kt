package contest.assignment.global.security.exception


import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.response.CustomResponse
import contest.assignment.global.security.util.HttpResponseUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(JwtAuthenticationEntryPoint::class.java)

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.error("** JwtAuthenticationEntryPoint **")

        val errorResponse = CustomResponse.fail(
            GeneralErrorCode.USER_AUTHENTICATION_FAIL,
            null
        )

        HttpResponseUtil.setErrorResponse(response, HttpStatus.UNAUTHORIZED, errorResponse)
    }
}
