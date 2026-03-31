package org.mahdi.ecommercebazzarly.repository

import org.mahdi.ecommercebazzarly.data.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
}