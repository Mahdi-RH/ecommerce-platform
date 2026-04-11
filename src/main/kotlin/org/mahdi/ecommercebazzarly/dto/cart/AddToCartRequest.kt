package org.mahdi.ecommercebazzarly.dto.cart

data class AddToCartRequest(
    val productId: Long,
    val quantity: Int
)