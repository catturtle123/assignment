package contest.assignment.domain.chat.entity


import contest.assignment.domain.user.entity.User
import contest.assignment.global.apiPayload.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "thread")
class Thread(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {
}