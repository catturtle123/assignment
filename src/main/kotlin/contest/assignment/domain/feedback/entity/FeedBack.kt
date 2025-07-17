package contest.assignment.domain.feedback.entity

import contest.assignment.global.apiPayload.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class FeedBack(
    val userID: String,

    val conversationID: String,

    val isPositive: Boolean,

    val feedBackStatus: Boolean,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    ) : BaseEntity() {
}