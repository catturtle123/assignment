package contest.assignment.domain.chat.repository

import contest.assignment.domain.chat.entity.Chat
import contest.assignment.domain.chat.entity.Thread
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository : JpaRepository<Chat, Long> {

    fun deleteAllByThread(thread: Thread)

    fun findByThread(thread: Thread, pageable: Pageable): Page<Chat>

}