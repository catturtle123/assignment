package contest.assignment.global.security.util

import com.fasterxml.jackson.databind.ObjectMapper
import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.exception.AuthException

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory


object HttpRequestUtil {

    private val log = LoggerFactory.getLogger(HttpRequestUtil::class.java)
    private val objectMapper = ObjectMapper()

    fun <T> readBody(request: HttpServletRequest, requestDTO: Class<T>): T {
        return try {
            objectMapper.readValue(request.inputStream, requestDTO)
        } catch (e: Exception) {
            log.error("Failed to parse request body: ${e.message}")
            throw AuthException(GeneralErrorCode.INVALID_REQUEST_BODY)
        }
    }

    fun readHeader(request: HttpServletRequest, headerName: String): String {
        val headerValue = request.getHeader(headerName)
        if (headerValue.isNullOrEmpty()) {
            throw AuthException(GeneralErrorCode.INVALID_REQUEST_HEADER)
        }
        return headerValue
    }
}
