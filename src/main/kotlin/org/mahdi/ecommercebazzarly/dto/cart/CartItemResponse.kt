package org.mahdi.ecommercebazzarly.dto.cart

data class CartItemResponse(
    val productId: Long,
    val name: String,
    val price: Double,
    val quantity: Int
)