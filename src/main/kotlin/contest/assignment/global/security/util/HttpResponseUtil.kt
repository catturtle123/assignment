package contest.assignment.global.security.util

import com.fasterxml.jackson.databind.ObjectMapper
import contest.assignment.global.apiPayload.code.GeneralSuccessCode
import contest.assignment.global.apiPayload.response.CustomResponse
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus

object HttpResponseUtil {

    private val log = LoggerFactory.getLogger(HttpResponseUtil::class.java)
    private val objectMapper = ObjectMapper()

    @JvmStatic
    fun setSuccessResponse(response: HttpServletResponse, status: GeneralSuccessCode, body: Any?) {
        try {
            val responseBody = objectMapper.writeValueAsString(CustomResponse.of(true, status.code, status.message, body))
            response.contentType = "application/json"
            response.status = status.httpStatus.value()
            response.characterEncoding = "UTF-8"
            response.writer.write(responseBody)
        } catch (e: Exception) {
            log.error("Failed to write success response: ${e.message}", e)
        }
    }

    @JvmStatic
    fun setErrorResponse(response: HttpServletResponse, httpStatus: HttpStatus, body: Any?) {
        try {
            response.contentType = "application/json"
            response.status = httpStatus.value()
            response.characterEncoding = "UTF-8"
            objectMapper.writeValue(response.outputStream, body)
        } catch (e: Exception) {
            log.error("Failed to write error response: ${e.message}", e)
        }
    }
}
