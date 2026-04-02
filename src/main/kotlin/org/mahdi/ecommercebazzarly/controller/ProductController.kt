package org.mahdi.ecommercebazzarly.controller

import org.mahdi.ecommercebazzarly.dto.product.ProductResponse
import org.mahdi.ecommercebazzarly.service.product.ProductService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun getProducts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "name") sort: String
    ): ResponseEntity<Page<ProductResponse>> {

        val products = productService.getProducts(page, size, sort)
        return ResponseEntity.ok(products)
    }
}