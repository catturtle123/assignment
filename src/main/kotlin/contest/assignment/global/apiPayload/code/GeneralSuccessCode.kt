package contest.assignment.global.apiPayload.code

import contest.assignment.global.apiPayload.code.BaseCode
import org.springframework.http.HttpStatus

enum class GeneralSuccessCode(
    override val httpStatus: HttpStatus,
    override val code: String,
    override val message: String,
) : BaseCode {

    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    _CREATED(HttpStatus.CREATED, "COMMON201", "요청 성공 및 리소스 생성되었습니다."),
    _DELETED(HttpStatus.NO_CONTENT, "COMMON204", "성공적으로 삭제했습니다."),
    ;

}
