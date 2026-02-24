package org.mahdi.ecommercebazzarly

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class WelcomeController {

    @GetMapping("/welcome")
    fun welcome(): String {
        return "welcome to mahdi!"
    }
}