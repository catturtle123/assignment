package contest.assignment.domain.user.dto.request

import contest.assignment.domain.user.entity.enums.Authority

data class CreateUserDTO(
    val email: String,
    val password: String,
    val name: String,
    val authority: Authority
)
