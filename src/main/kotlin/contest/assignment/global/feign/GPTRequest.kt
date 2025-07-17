package contest.assignment.global.feign

data class GPTRequest (
    val model: String = "gpt-4.1",
    val input: String
)
