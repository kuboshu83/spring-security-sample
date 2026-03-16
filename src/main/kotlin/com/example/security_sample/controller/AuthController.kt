package com.example.security_sample.controller

import com.example.security_sample.auth.AuthUserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class NewUserDTO(
    val name: String,
    val password: String,
)

@RestController
@RequestMapping("/auth")
class AuthController(
    private val service: AuthUserService
) {
    @PostMapping
    fun createGeneralUser(
        @RequestBody user: NewUserDTO,
    ) {
        println("${user.name}, ${user.password}")
        service.createGeneralUser(user.name, user.password)
    }
}