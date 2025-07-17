package contest.assignment.domain.chat.entity

import contest.assignment.global.apiPayload.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "chat")
class Chat(

    @Column(length = 10000)
    val question: String,

    @Column(length = 10000)
    val answer: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id", nullable = false)
    var thread: Thread,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity()