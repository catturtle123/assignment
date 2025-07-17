package contest.assignment.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun umcStudyAPI(): OpenAPI {
        val jwtSchemeName = "JWT TOKEN"

        val info = Info()
            .title("UMC AOS server API")
            .description("UMC AOS Server API 명세서")
            .version("1.0.0")

        val securityRequirement = SecurityRequirement().addList(jwtSchemeName)

        val components = Components().addSecuritySchemes(
            jwtSchemeName,
            SecurityScheme()
                .name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        )

        return OpenAPI()
            .addServersItem(Server().url("/"))
            .info(info)
            .addSecurityItem(securityRequirement)
            .components(components)
    }
}