package org.mahdi.ecommercebazzarly.service.user

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.mahdi.ecommercebazzarly.service.user.model.CustomUserPrincipal
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
//    private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        if (SecurityContextHolder.getContext().authentication == null) {
            val token = authHeader.substring(7)
//            val userDetails = userDetailsService.loadUserByUsername(email)
            val userId = jwtService.extractUserId(token)
            val email = jwtService.extractEmail(token)

            val principal = CustomUserPrincipal(
                userId = userId,
                email = email,
                password = "",
                authorities = emptyList()
            )


            if (jwtService.isTokenValid(token, principal)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    principal.authorities
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }
}