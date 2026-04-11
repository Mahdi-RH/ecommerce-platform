package org.mahdi.ecommercebazzarly.repository

import org.mahdi.ecommercebazzarly.data.model.cart.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {
    fun findByUserId(userId: Long): Cart?
}