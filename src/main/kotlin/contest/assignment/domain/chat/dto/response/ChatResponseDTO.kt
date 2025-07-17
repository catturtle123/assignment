package contest.assignment.domain.chat.dto.response

import java.time.LocalDateTime

data class ChatResponseDTO(
    val chatId: Long?,
    val question: String,
    val answer: String,
    val createdAt: LocalDateTime,
)