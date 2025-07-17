package contest.assignment.global.security.exception

import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.response.CustomResponse
import contest.assignment.global.security.util.HttpResponseUtil
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {

    @Throws(IOException::class, ServletException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val errorResponse = CustomResponse.fail(GeneralErrorCode.USER_INSUFFICIENT_PERMISSION, null)

        HttpResponseUtil.setErrorResponse(response, HttpStatus.FORBIDDEN, errorResponse)
    }
}
