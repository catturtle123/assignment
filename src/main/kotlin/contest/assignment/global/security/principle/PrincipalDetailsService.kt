package contest.assignment.global.security.principle

import contest.assignment.domain.user.repository.UserRepository
import contest.assignment.global.apiPayload.code.GeneralErrorCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class PrincipalDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val member = userRepository.findByEmail(email)
            .orElseThrow { UsernameNotFoundException(GeneralErrorCode.MEMBER_NOT_FOUND.message) }

        return PrincipalDetails(member)
    }
}
