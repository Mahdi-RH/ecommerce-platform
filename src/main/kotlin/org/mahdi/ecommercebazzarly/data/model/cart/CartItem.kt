package org.mahdi.ecommercebazzarly.data.model.cart

import jakarta.persistence.*
import org.mahdi.ecommercebazzarly.data.model.Product

@Entity
@Table(
    name = "cart_items",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["cart_id", "product_id"])
    ]
)
class CartItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    val cart: Cart,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,

    @Column(nullable = false)
    var quantity: Int
)