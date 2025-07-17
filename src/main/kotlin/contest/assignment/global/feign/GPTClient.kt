package contest.assignment.global.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import contest.assignment.global.feign.dto.GptResponse

@FeignClient(name = "gptClient", url = "\${external.gpt.url}", configuration = [GPTConfiguration::class])
interface GPTClient {

    @PostMapping("/v1/responses")
    fun gptResponse(@RequestBody req: GPTRequest): GptResponse

}