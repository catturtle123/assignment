package contest.assignment.global.apiPayload.code

import contest.assignment.global.apiPayload.code.BaseErrorCode
import org.springframework.http.HttpStatus

enum class GeneralErrorCode(
    override val httpStatus: HttpStatus,
    override val code: String,
    override val message: String
): BaseErrorCode {

    // 기본 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "리소스를 찾을 수 없습니다."),
    _IS_ALREADY(HttpStatus.BAD_REQUEST, "COMMON400", "이미 해당 리소스가 존재합니다."),
    NOT_VALID_CURSOR(HttpStatus.BAD_REQUEST, "PAGE_001", "커서 값이 유효하지 않습니다."),
    NOT_VALID_SIZE(HttpStatus.BAD_REQUEST, "PAGE_004", "size 값이 유효하지 않습니다."),
    _DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "DB 에러, 관리자에게 문의 바랍니다."),
    _ENUM_ERROR(HttpStatus.BAD_REQUEST, "COMMON400", "해당되는 enum 값이 들어있지 않습니다."),

    // GPT 에러
    EXTERNAL_GPT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "GPT와의 외부 연동이 실패하였습니다.")
    ;

}