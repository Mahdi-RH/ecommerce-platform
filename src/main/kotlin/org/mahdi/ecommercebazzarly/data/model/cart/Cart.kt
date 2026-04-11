package org.mahdi.ecommercebazzarly.data.model.cart

import jakarta.persistence.*
import org.mahdi.ecommercebazzarly.dto.cart.CartItemResponse
import org.mahdi.ecommercebazzarly.dto.cart.CartResponse
import java.time.LocalDateTime

@Entity
@Table(name = "carts")
class Cart(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id", nullable = false, unique = true)
    val userId: Long,

    @OneToMany(
        mappedBy = "cart",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val items: MutableList<CartItem> = mutableListOf(),

    val createdAt: LocalDateTime = LocalDateTime.now()
)

fun Cart.toResponse(): CartResponse {
    val items = this.items.map {
        CartItemResponse(
            productId = it.product.id,
            name = it.product.name,
            price = it.product.price.toDouble(),
            quantity = it.quantity
        )
    }
    val totalPrice = items.sumOf { it.price * it.quantity }
    return CartResponse(items, totalPrice)
}