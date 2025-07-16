package contest.assignment.global.apiPayload.code

import org.springframework.http.HttpStatus

interface BaseCode {
    val httpStatus: HttpStatus
    val code: String
    val message: String
}