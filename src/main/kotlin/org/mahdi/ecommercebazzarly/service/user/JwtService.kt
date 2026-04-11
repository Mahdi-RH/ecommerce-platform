package org.mahdi.ecommercebazzarly.service.user

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.mahdi.ecommercebazzarly.data.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.mahdi.ecommercebazzarly.config.JwtProperties
import java.util.Date

@Service
class JwtService(
    private val jwtProperties: JwtProperties
) {

    private val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret))

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email)
            .claim("userId", user.id)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.expiration.toMillis()))
            .signWith(key)
            .compact()
    }

    fun extractEmail(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun extractUserId(token: String): Long {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        return claims["userId"].toString().toLong()
    }

    fun isTokenValid(token: String, user: UserDetails): Boolean {
        val email = extractEmail(token)
        return email == user.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .expiration

        return expiration.before(Date())
    }
}