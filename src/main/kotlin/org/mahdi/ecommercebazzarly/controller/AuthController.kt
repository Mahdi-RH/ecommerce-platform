package org.mahdi.ecommercebazzarly.controller

import jakarta.validation.Valid
import org.mahdi.ecommercebazzarly.dto.login.LoginRequest
import org.mahdi.ecommercebazzarly.dto.login.LoginResponse
import org.mahdi.ecommercebazzarly.dto.register.RegisterRequest
import org.mahdi.ecommercebazzarly.dto.register.RegisterResponse
import org.mahdi.ecommercebazzarly.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<RegisterResponse> {
        val response = userService.register(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val response = userService.login(request)
        return ResponseEntity.ok(response)
    }
}