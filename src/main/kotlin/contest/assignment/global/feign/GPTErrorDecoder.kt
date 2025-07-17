package contest.assignment.global.feign

import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.exception.APIException
import feign.Response
import feign.codec.ErrorDecoder

class GPTErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        try {
            throw APIException(GeneralErrorCode.EXTERNAL_GPT_ERROR);
        } catch (e: Exception) {
            throw APIException(GeneralErrorCode.EXTERNAL_GPT_ERROR);
        }
    }


}