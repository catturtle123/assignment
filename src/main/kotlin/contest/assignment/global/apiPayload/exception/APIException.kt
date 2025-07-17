package contest.assignment.global.apiPayload.exception

import contest.assignment.global.apiPayload.code.BaseErrorCode

class APIException (
    errorCode: BaseErrorCode,
): GeneralException(errorCode)