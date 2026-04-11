package org.mahdi.ecommercebazzarly.repository

import org.mahdi.ecommercebazzarly.data.model.cart.CartItem
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository : JpaRepository<CartItem, Long>