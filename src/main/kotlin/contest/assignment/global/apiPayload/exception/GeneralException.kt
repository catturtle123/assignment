package contest.assignment.global.apiPayload.exception

import contest.assignment.global.apiPayload.code.BaseErrorCode

open class GeneralException(
    val errorCode: BaseErrorCode
) : RuntimeException(errorCode.message)