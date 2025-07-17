package contest.assignment.domain.chat.repository

import contest.assignment.domain.chat.entity.Chat
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository: JpaRepository<Chat, Long> {
}