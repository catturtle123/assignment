package contest.assignment.domain.chat.repository

import contest.assignment.domain.chat.entity.Thread
import contest.assignment.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ThreadRepository: JpaRepository<Thread, Long> {

    fun findByUser(user: User): Optional<Thread>
}