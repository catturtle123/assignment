package contest.assignment.global.apiPayload.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import contest.assignment.global.apiPayload.code.BaseCode
import contest.assignment.global.apiPayload.code.BaseErrorCode
import contest.assignment.global.apiPayload.code.GeneralSuccessCode

@JsonPropertyOrder("isSuccess", "code", "message", "result")
data class CustomResponse<T>(
    @JsonProperty("isSuccess")
    var isSuccess: Boolean,

    @JsonProperty("message")
    var message: String,

    @JsonProperty("code")
    var code: String,

    @JsonProperty("result")
    var result: T? = null
) {
    companion object {
        fun <T> ok(result: T): CustomResponse<T> {
            return success(GeneralSuccessCode._OK, result)
        }

        fun <T> created(result: T): CustomResponse<T> {
            return success(GeneralSuccessCode._CREATED, result)
        }

        fun <T> success(code: BaseCode, result: T): CustomResponse<T> {
            return of(true, code.message, code.code, result)
        }

        fun <T> fail(code: BaseErrorCode, result: T): CustomResponse<T> {
            return of(false, code.message, code.code, result)
        }

        fun <T> of(isSuccess: Boolean, message: String, code: String, result: T): CustomResponse<T> {
            return CustomResponse(isSuccess, message, code, result)
        }
    }

    fun message(message: String): CustomResponse<T> {
        this.message = message
        return this
    }

    fun body(result: T): CustomResponse<T> {
        this.result = result
        return this
    }
}
