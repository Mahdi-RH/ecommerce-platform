package org.mahdi.ecommercebazzarly.dto.cart

data class CartResponse(
    val items: List<CartItemResponse>,
    val totalPrice: Double
)