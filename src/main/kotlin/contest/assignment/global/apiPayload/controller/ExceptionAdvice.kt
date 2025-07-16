package contest.assignment.global.apiPayload.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import contest.assignment.global.apiPayload.code.BaseErrorCode
import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.exception.GeneralException
import contest.assignment.global.apiPayload.response.CustomResponse
import mu.KotlinLogging


@RestControllerAdvice(annotations = [RestController::class])
class ExceptionAdvice {

    private val log = KotlinLogging.logger {}

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): ResponseEntity<CustomResponse<List<String>>> {
        log.error(e.stackTraceToString())

        val message = e.constraintViolations
            .map { it.message }

        val code: BaseErrorCode = GeneralErrorCode._BAD_REQUEST
        log.error("Exception Advice(ConstraintViolationException): {}", message)

        return ResponseEntity.status(code.httpStatus).body(CustomResponse.fail(code, message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<CustomResponse<Map<String, String>>> {
        val error = e.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid value") }
        val code: BaseErrorCode = GeneralErrorCode._BAD_REQUEST

        log.error("Exception Advice(MethodArgumentNotValidException): {}", error)
        return ResponseEntity.status(code.httpStatus).body(CustomResponse.fail(code, error))
    }

    @ExceptionHandler(GeneralException::class)
    fun generalException(e: GeneralException): ResponseEntity<CustomResponse<String?>> {
        val code: BaseErrorCode = e.errorCode
        return ResponseEntity.status(code.httpStatus).body(CustomResponse.fail(code, null))
    }

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception, request: HttpServletRequest): ResponseEntity<CustomResponse<String>> {
        val code: BaseErrorCode = GeneralErrorCode._INTERNAL_SERVER_ERROR
        val message = "${e.javaClass.simpleName}: ${e.message}"

        return ResponseEntity.status(code.httpStatus).body(CustomResponse.fail(code, message))
    }
}
