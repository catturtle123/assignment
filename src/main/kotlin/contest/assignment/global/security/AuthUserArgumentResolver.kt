package contest.assignment.global.security

import contest.assignment.domain.user.entity.User
import contest.assignment.domain.user.service.UserService
import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.exception.GeneralException
import org.springframework.stereotype.Component

import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthUserArgumentResolver(
    private val userService: UserService
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == User::class.java &&
                parameter.hasParameterAnnotation(AuthUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication == null || authentication.name == "anonymousUser") {
            throw GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND)
        }

        val userId = authentication.name.toLongOrNull()
            ?: throw GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND)

        return userService.findById(userId)
    }
}