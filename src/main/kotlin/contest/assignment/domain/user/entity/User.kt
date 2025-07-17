package contest.assignment.domain.user.entity

import contest.assignment.domain.user.dto.request.CreateUserDTO
import contest.assignment.domain.user.entity.enums.Authority
import contest.assignment.global.apiPayload.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(

    @Column
    val email: String,

    @Column
    val password: String,

    @Column
    val name: String,

    @Column
    val authority: Authority,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseEntity() {
    companion object {
        fun of(createUserDTO: CreateUserDTO): User {
            return User(createUserDTO.email, createUserDTO.password, createUserDTO.name, createUserDTO.authority)
        }
    }
}