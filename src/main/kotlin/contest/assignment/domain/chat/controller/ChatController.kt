package contest.assignment.domain.chat.controller

import contest.assignment.domain.chat.dto.request.CreateChatRequest
import contest.assignment.domain.chat.dto.response.CreateChatResponse
import contest.assignment.domain.chat.service.ChatService
import contest.assignment.domain.user.entity.User
import contest.assignment.global.apiPayload.response.CustomResponse
import contest.assignment.global.security.AuthUser
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("/api/v1/chats")
    fun createChat(
        @RequestBody createChatRequest: CreateChatRequest,
        @AuthUser user: User
    ): CustomResponse<CreateChatResponse> {
        println(user)
        return CustomResponse.ok(chatService.createChat(createChatRequest, user))
    }

}