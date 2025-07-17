package contest.assignment.domain.chat.controller

import contest.assignment.domain.chat.dto.request.CreateChatRequest
import contest.assignment.domain.chat.dto.response.CreateChatResponse
import contest.assignment.domain.chat.service.ChatService
import contest.assignment.domain.user.entity.User
import contest.assignment.global.apiPayload.response.CustomResponse
import contest.assignment.global.security.AuthUser
import org.springframework.web.bind.annotation.*

@RestController
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("/api/v1/chats")
    fun createChat(
        @RequestBody createChatRequest: CreateChatRequest,
        @AuthUser user: User
    ): CustomResponse<CreateChatResponse> {
        return CustomResponse.ok(chatService.createChat(createChatRequest, user))
    }

    @DeleteMapping("/api/v1/threads/{threadId}")
    fun deleteChat(
        @PathVariable threadId: Long,
    ): CustomResponse<String> {
        chatService.deleteThread(threadId)
        return CustomResponse.ok("삭제 성공")
    }
}