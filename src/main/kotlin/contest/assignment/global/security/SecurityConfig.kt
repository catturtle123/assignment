package contest.assignment.global.security

import contest.assignment.domain.user.repository.UserRepository
import contest.assignment.global.security.exception.JwtAccessDeniedHandler
import contest.assignment.global.security.exception.JwtAuthenticationEntryPoint
import contest.assignment.global.security.filter.JwtFilter
import contest.assignment.global.security.util.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val jwtUtil: JwtUtil
) {

    companion object {
        private val allowUrls = arrayOf(
            "/swagger-ui/**",
            "/v3/**",
            "/api-docs/**",
            "/api/v1/users",
            "/api/v1/login",
            "/health"
        )
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity, userRepository: UserRepository): SecurityFilterChain {

        http.csrf { it.disable() }

        http.authorizeHttpRequests {
            it.requestMatchers(*allowUrls).permitAll()
                .requestMatchers("/**").authenticated()
                .anyRequest().permitAll()
        }

        http.formLogin { it.disable() }
        http.httpBasic { it.disable() }

        http.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.exceptionHandling {
            it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        }

        http.addFilterBefore(JwtFilter(jwtUtil, allowUrls), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}
