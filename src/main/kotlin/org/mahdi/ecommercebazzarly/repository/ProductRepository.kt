package org.mahdi.ecommercebazzarly.repository

import org.mahdi.ecommercebazzarly.data.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findByStockGreaterThan(stock: Int, pageable: Pageable): Page<Product>
}