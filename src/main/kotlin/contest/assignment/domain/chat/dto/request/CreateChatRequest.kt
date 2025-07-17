package contest.assignment.domain.chat.dto.request

class CreateChatRequest(
    val question: String,
    val isStreaming: Boolean,
    val model: String
)