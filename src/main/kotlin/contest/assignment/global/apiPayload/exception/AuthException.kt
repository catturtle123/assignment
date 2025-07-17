package contest.assignment.global.apiPayload.exception

import contest.assignment.global.apiPayload.code.BaseErrorCode

class AuthException (
    errorCode: BaseErrorCode,
): GeneralException(errorCode)