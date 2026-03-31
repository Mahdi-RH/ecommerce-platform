package org.mahdi.ecommercebazzarly.exception.model

data class ApiError(
    val code: String,
    val message: String?,
    val timestamp: Long = System.currentTimeMillis()
)