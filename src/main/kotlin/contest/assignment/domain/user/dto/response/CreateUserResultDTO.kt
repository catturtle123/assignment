package contest.assignment.domain.user.dto.response

import contest.assignment.domain.user.entity.User
import java.time.LocalDateTime

data class CreateUserResultDTO(

    val updatedAt: LocalDateTime,

    val createdAt: LocalDateTime,

    val id: Long?,
) {
    companion object {
        fun from(user: User): CreateUserResultDTO {
            return CreateUserResultDTO(user.updatedAt, user.createdAt, user.id)
        }
    }
}
