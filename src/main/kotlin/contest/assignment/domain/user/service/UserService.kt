package contest.assignment.domain.user.service

import contest.assignment.domain.user.dto.request.CreateUserDTO
import contest.assignment.domain.user.dto.request.LoginDTO
import contest.assignment.domain.user.dto.response.CreateUserResultDTO
import contest.assignment.domain.user.dto.response.LoginResultDTO
import contest.assignment.domain.user.entity.User
import contest.assignment.domain.user.repository.UserRepository
import contest.assignment.global.apiPayload.code.GeneralErrorCode
import contest.assignment.global.apiPayload.exception.AuthException
import contest.assignment.global.security.principle.PrincipalDetails
import contest.assignment.global.security.util.JwtUtil
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    @Transactional
    fun createUser(
        createUserDTO: CreateUserDTO
    ): CreateUserResultDTO {

        if (userRepository.existsByEmail(createUserDTO.email)) {
            throw AuthException(GeneralErrorCode.USER_ALREADY)
        }

        val encodedPassword = passwordEncoder.encode(createUserDTO.password)

        val user = User.of(createUserDTO.copy(password = encodedPassword))

        val savedUser = userRepository.save(user)

        return CreateUserResultDTO.from(savedUser)
    }

    @Transactional(readOnly = true)
    fun login(loginDTO: LoginDTO): LoginResultDTO {
        val user: User = userRepository.findByEmail(loginDTO.email)
            .orElseThrow { UsernameNotFoundException(GeneralErrorCode.MEMBER_NOT_FOUND.message) }

        val isPasswordValid = passwordEncoder.matches(loginDTO.password, user.password)

        if (!isPasswordValid) {
            throw AuthException(GeneralErrorCode.BAD_CREDENTIALS)
        }

        val principalDetails = PrincipalDetails(user)

        val accessToken = jwtUtil.createAccessToken(principalDetails)

        return LoginResultDTO(id = user.id, accessToken)
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): User {
        return userRepository.findById(id).get()
    }

}