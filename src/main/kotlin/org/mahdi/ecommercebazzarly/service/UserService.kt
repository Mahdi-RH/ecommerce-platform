package org.mahdi.ecommercebazzarly.service

import org.mahdi.ecommercebazzarly.data.model.User
import org.mahdi.ecommercebazzarly.dto.login.LoginRequest
import org.mahdi.ecommercebazzarly.dto.login.LoginResponse
import org.mahdi.ecommercebazzarly.dto.register.RegisterRequest
import org.mahdi.ecommercebazzarly.dto.register.RegisterResponse
import org.mahdi.ecommercebazzarly.exception.EmailAlreadyExistsException
import org.mahdi.ecommercebazzarly.exception.InvalidCredentialsException
import org.mahdi.ecommercebazzarly.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: PasswordEncoder,
                  private val jwtService: JwtService) {

    fun register(request: RegisterRequest): RegisterResponse {

        if (userRepository.existsByEmail(request.email)) {
            throw EmailAlreadyExistsException()
        }
        val user = User(email = request.email, password = passwordEncoder.encode(request.password))
                val saved = userRepository.save(user)
        return RegisterResponse(saved.id, saved.email)
    }

    fun login(request: LoginRequest): LoginResponse {

        val user = userRepository.findByEmail(request.email)
            ?: throw InvalidCredentialsException()

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialsException()
        }

        val token = jwtService.generateToken(user)  // TODO(how to use spring instead of generationg it manually? )

        return LoginResponse(token)
    }

}