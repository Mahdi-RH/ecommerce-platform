package org.mahdi.ecommercebazzarly.data.model

import jakarta.persistence.*
import org.mahdi.ecommercebazzarly.dto.product.ProductResponse
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    val description: String?,

    @Column(nullable = false)
    val price: BigDecimal,

    @Column(nullable = false)
    val stock: Int,

    val createdAt: LocalDateTime = LocalDateTime.now()
)

fun Product.toResponse() = ProductResponse(
    id = id,
    name = name,
    description = description,
    price = price.toDouble(),
    stock = stock
)