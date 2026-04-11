package org.mahdi.ecommercebazzarly.controller

import org.mahdi.ecommercebazzarly.data.model.cart.toResponse
import org.mahdi.ecommercebazzarly.dto.cart.AddToCartRequest
import org.mahdi.ecommercebazzarly.dto.cart.CartResponse
import org.mahdi.ecommercebazzarly.service.CartService
import org.mahdi.ecommercebazzarly.service.user.model.CustomUserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/cart")
class CartController(
    private val cartService: CartService
){

    @PostMapping("/items")
    fun addToCart(
        @RequestBody request: AddToCartRequest,
        authentication: Authentication
    ): ResponseEntity<CartResponse> {

        val userId = getUserId(authentication)

        val cart = cartService.addToCart(
            userId,
            request.productId,
            request.quantity
        )

        return ResponseEntity.ok(cart.toResponse())
    }

    @DeleteMapping("/items/{productId}")
    fun removeFromCart(
        @PathVariable productId: Long,
        authentication: Authentication
    ): ResponseEntity<CartResponse> {

        val userId = getUserId(authentication)

        val cart = cartService.removeFromCart(userId, productId)

        return ResponseEntity.ok(cart.toResponse())
    }

    @GetMapping
    fun getCart(authentication: Authentication): ResponseEntity<CartResponse> {

        val userId = getUserId(authentication)

        val cart = cartService.getCart(userId)

        return ResponseEntity.ok(cart.toResponse())
    }

    private fun getUserId(authentication: Authentication): Long {
        val principal = authentication.principal as CustomUserPrincipal
        return principal.userId
    }

}