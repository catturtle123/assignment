package contest.assignment.global.feign

import contest.assignment.global.feign.GPTErrorDecoder
import feign.RequestInterceptor
import feign.codec.ErrorDecoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class GPTConfiguration {

    @Bean
    fun requestInterceptor(
        @Value("\${external.gpt.key}") gptKey: String
    ): RequestInterceptor {
        return RequestInterceptor { request ->
            request.header("Authorization", "Bearer $gptKey")
        }
    }

    @Bean
    fun gptErrorDecode(): ErrorDecoder {
        return GPTErrorDecoder()
    }
}