package org.mahdi.ecommercebazzarly.service.product

import org.mahdi.ecommercebazzarly.data.model.toResponse
import org.mahdi.ecommercebazzarly.dto.product.ProductResponse
import org.mahdi.ecommercebazzarly.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun getProducts(page: Int, size: Int, sort: String): Page<ProductResponse> {

        val pageable = PageRequest.of(page, size, Sort.by(sort).ascending())

        return productRepository
            .findByStockGreaterThan(0, pageable)
            .map { it.toResponse() }
    }
}