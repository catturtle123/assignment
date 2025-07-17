package contest.assignment.global.feign.dto

data class GptResponse(
    val output: List<Output>
)

data class Output(
    val content: List<Content>
)

data class Content(
    val text: String
)

