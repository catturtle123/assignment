package contest.assignment.domain.user.controller

import contest.assignment.domain.user.dto.request.CreateUserDTO
import contest.assignment.domain.user.dto.request.LoginDTO
import contest.assignment.domain.user.dto.response.CreateUserResultDTO
import contest.assignment.domain.user.dto.response.LoginResultDTO
import contest.assignment.domain.user.service.UserService
import contest.assignment.global.apiPayload.response.CustomResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/api/v1/users")
    fun createExample(
        @RequestBody createUserDTO: CreateUserDTO
    ): CustomResponse<CreateUserResultDTO> {
        return CustomResponse.ok(userService.createUser(createUserDTO))
    }

    @PostMapping("/api/v1/login")
    fun login(
        @RequestBody loginDTO: LoginDTO
    ): CustomResponse<LoginResultDTO> {
        return CustomResponse.ok(userService.login(loginDTO))
    }

}