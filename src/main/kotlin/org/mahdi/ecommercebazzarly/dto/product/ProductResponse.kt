package org.mahdi.ecommercebazzarly.dto.product

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val price: Double,
    val stock: Int
)