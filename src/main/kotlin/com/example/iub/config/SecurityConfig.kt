package com.example.iub.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.security.web.AuthenticationEntryPoint
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Configuration
@EnableMethodSecurity // enables @PreAuthorize on your controllers/services
class SecurityConfig(
    private val jwtFilter: JwtAuthFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/auth/**",
                    "/v3/api-docs/**",        // <-- allow OpenAPI JSON
                    "/swagger-ui/**",         // <-- allow Swagger UI assets
                    "/swagger-ui.html"        // <-- UI landing
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    // --- Optional helpers below ---

    @Bean
    fun corsSource(): CorsConfigurationSource {
        val cfg = CorsConfiguration()
        cfg.allowedOrigins = listOf("*")          // tighten in prod (e.g., your frontend URL)
        cfg.allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        cfg.allowedHeaders = listOf("Authorization", "Content-Type")
        cfg.exposedHeaders = listOf("Authorization")
        cfg.allowCredentials = false
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", cfg)
        return source
    }

    @Bean
    fun unauthorizedEntryPoint(): AuthenticationEntryPoint =
        AuthenticationEntryPoint { _: HttpServletRequest, res: HttpServletResponse, _: Exception ->
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED) // 401 on missing/invalid token
        }
}
