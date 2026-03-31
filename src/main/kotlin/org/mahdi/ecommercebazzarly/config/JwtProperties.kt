package org.mahdi.ecommercebazzarly.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "ecommerce.bazzarly.security.jwt")
data class JwtProperties(
    val secret: String,
    val expiration: Duration
)