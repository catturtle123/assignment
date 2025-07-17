package contest.assignment.domain.feedback.repository

import contest.assignment.domain.feedback.entity.FeedBack
import org.springframework.data.jpa.repository.JpaRepository

interface FeedBackRepository: JpaRepository<FeedBack, Long> {
}