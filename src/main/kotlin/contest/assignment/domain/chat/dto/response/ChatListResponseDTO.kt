package contest.assignment.domain.chat.dto.response

class ChatListResponseDTO(
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val results: List<ChatResponseDTO>
)