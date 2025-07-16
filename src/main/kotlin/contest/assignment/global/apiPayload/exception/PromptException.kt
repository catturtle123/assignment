package contest.assignment.global.apiPayload.exception

import contest.assignment.global.apiPayload.code.BaseErrorCode

class PromptException(
    errorCode: BaseErrorCode,
): GeneralException(errorCode)