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
    EXTERNAL_GPT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "GPT와의 외부 연동이 실패하였습니다."),

    // 공통 에러
    PAGE_UNDER_ZERO(HttpStatus.BAD_REQUEST, "COMM_001", "페이지는 0이상이어야 합니다."),
    INVALID_SORT_CONDITION(HttpStatus.BAD_REQUEST, "COMM_002", "유효하지 않은 정렬 조건입니다."),

    // MEMBER 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_001", "MEMBER가 존재하지 않습니다."),

    // AUTH 에러
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_001", "토큰이 만료되었습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_002", "토큰이 유효하지 않습니다."),
    AUTH_UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_003", "지원하지 않는 토큰 형식입니다."),
    AUTH_MALFORMED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_004", "토큰 형식이 올바르지 않습니다."),
    AUTH_SIGNATURE_INVALID(HttpStatus.UNAUTHORIZED, "AUTH_005", "토큰 서명이 유효하지 않습니다."),
    AUTH_TOKEN_EMPTY(HttpStatus.BAD_REQUEST, "AUTH_006", "토큰이 비어있습니다."),
    AUTH_TOKEN_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH_007", "토큰 처리 중 오류가 발생했습니다."),

    // 기존 AUTH 에러들은 번호 조정
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "AUTH_008", "비밀번호를 잘못 입력했습니다."),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH_009", "인증에 실패했습니다."),
    USER_AUTHENTICATION_FAIL(HttpStatus.UNAUTHORIZED, "AUTH_010", "유저 인증에 실패했습니다."),
    USER_INSUFFICIENT_PERMISSION(HttpStatus.FORBIDDEN, "AUTH_011", "권한이 부족한 사용자 입니다."),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "AUTH_012", "잘못된 요청 본문입니다."),
    INVALID_REQUEST_HEADER(HttpStatus.BAD_REQUEST, "AUTH_013", "잘못된 요청 헤더입니다."),
    USER_UNREGISTERED(HttpStatus.UNAUTHORIZED, "AUTH_014", "존재하지 않는 계정입니다. 회원가입을 진행해주세요."),
    USER_ALREADY(HttpStatus.BAD_REQUEST, "AUTH_015", "이미 있는 유저입니다.");

    ;

}