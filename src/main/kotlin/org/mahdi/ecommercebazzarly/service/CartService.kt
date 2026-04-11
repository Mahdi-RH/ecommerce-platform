package org.mahdi.ecommercebazzarly.service

import jakarta.transaction.Transactional
import org.mahdi.ecommercebazzarly.data.model.cart.Cart
import org.mahdi.ecommercebazzarly.data.model.cart.CartItem
import org.mahdi.ecommercebazzarly.repository.CartRepository
import org.mahdi.ecommercebazzarly.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
){

    @Transactional
    fun addToCart(userId: Long, productId: Long, quantity: Int): Cart {

        if (quantity <= 0) {
            throw IllegalArgumentException("Quantity must be greater than zero")
        }

        val product = productRepository.findById(productId)
            .orElseThrow { RuntimeException("Product not found") }

        val cart = cartRepository.findByUserId(userId)
            ?: cartRepository.save(Cart(userId = userId))

        val existingItem = cart.items.find { it.product.id == productId }

        if (existingItem != null) {
            val newQuantity = existingItem.quantity + quantity

            if (newQuantity > product.stock) {
                throw RuntimeException("Insufficient stock")
            }

            existingItem.quantity = newQuantity
        } else {
            if (quantity > product.stock) {
                throw RuntimeException("Insufficient stock")
            }

            val newItem = CartItem(cart = cart, product = product, quantity = quantity)
            cart.items.add(newItem)
        }

        return cartRepository.save(cart)
    }

    @Transactional
    fun removeFromCart(userId: Long, productId: Long): Cart {

        val cart = cartRepository.findByUserId(userId)
            ?: throw RuntimeException("Cart not found")

        val item = cart.items.find { it.product.id == productId }
            ?: throw RuntimeException("Product not in cart")

        cart.items.remove(item)

        return cartRepository.save(cart)
    }

    fun getCart(userId: Long): Cart {
        return cartRepository.findByUserId(userId)
            ?: Cart(userId = userId)
    }
}