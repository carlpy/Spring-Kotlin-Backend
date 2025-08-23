package com.example.iub.config

import com.example.iub.services.JwtServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwt: JwtServiceImpl // your interface that validates/extracts claims
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (header?.startsWith("Bearer ") == true) {
            val token = header.substring(7)
            if (jwt.validate(token)) {
                val email = jwt.getEmail(token)
                val role = jwt.getRole(token) // e.g. "ROLE_CLIENTE"
                val authorities = listOf(SimpleGrantedAuthority(role))

                val auth = UsernamePasswordAuthenticationToken(email, null, authorities).apply {
                    // optional: keep uid handy
                    details = mapOf("uid" to jwt.getUserId(token))
                }
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        filterChain.doFilter(request, response)
    }
}

