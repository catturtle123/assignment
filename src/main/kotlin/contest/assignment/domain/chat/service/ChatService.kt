package contest.assignment.domain.chat.service

import contest.assignment.domain.chat.dto.request.CreateChatRequest
import contest.assignment.domain.chat.dto.response.CreateChatResponse
import contest.assignment.domain.chat.entity.Chat
import contest.assignment.domain.chat.entity.Thread
import contest.assignment.domain.chat.repository.ChatRepository
import contest.assignment.domain.chat.repository.ThreadRepository
import contest.assignment.domain.user.entity.User
import contest.assignment.global.feign.GPTClient
import contest.assignment.global.feign.GPTRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDateTime

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val threadRepository: ThreadRepository,
    private val gptClient: GPTClient
) {

    @Transactional
    fun createChat(createChatRequest: CreateChatRequest, user: User): CreateChatResponse {

        val existingThreads = threadRepository.findByUser(user)

        val thread = if (existingThreads.isEmpty()) {
            threadRepository.save(Thread(user))  // 새로 생성
        } else {
            val recentThread = existingThreads.get()
            val createdAt = recentThread.createdAt
            val now = LocalDateTime.now()

            if (Duration.between(createdAt, now).toMinutes() > 30) {
                threadRepository.save(Thread(user))  // 30분 이상 경과 시 새로 생성
            } else {
                recentThread  // 기존 thread 재사용
            }
        }

        if (createChatRequest.isStreaming) {
            throw UnsupportedOperationException("Streaming 응답은 아직 지원되지 않습니다.")
        }

        val gptRequest = GPTRequest(input = createChatRequest.question, model = createChatRequest.model)
        val gptResponse = gptClient.gptResponse(gptRequest)

        val chat = Chat(
            question = createChatRequest.question,
            answer = gptResponse.output[0].content[0].text ?: "",
            thread = thread
        )

        val savedChat = chatRepository.save(chat)

        return CreateChatResponse(
            question = savedChat.question,
            answer = savedChat.answer,
        )
    }

    @Transactional
    fun deleteThread(threadId: Long) {
        val existingThreads: Thread = threadRepository.findById(threadId).orElseThrow{
            IllegalArgumentException("thread가 존재하지 않습니다")
        }

        chatRepository.deleteAllByThread(existingThreads)

        threadRepository.delete(existingThreads)
    }


}