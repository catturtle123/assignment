package contest.assignment.global.apiPayload.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckingController {

    @GetMapping("/healthcheck")
    fun healthCheck(): String {
        return "I'm healthy"
    }

}