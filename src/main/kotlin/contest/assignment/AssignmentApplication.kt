package contest.assignment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class AssignmentApplication

fun main(args: Array<String>) {
	runApplication<AssignmentApplication>(*args)
}
