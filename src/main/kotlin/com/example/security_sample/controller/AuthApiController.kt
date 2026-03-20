package com.example.security_sample.controller

import com.example.security_sample.auth.AuthUserService
import com.example.security_sample.auth.domain.AuthUserRole
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

data class NewUserDTO(
    val name: String,
    val password: String,
    val role: String,
)

@RestController
@RequestMapping("/api/auth")
class ApiAuthController(
    private val service: AuthUserService
) {
    @PostMapping("/new")
    fun createUser(
        @RequestBody user: NewUserDTO,
    ) {
        println("${user.name}, ${user.password}, ${user.role}")
        val role: AuthUserRole = when (user.role) {
            "general" -> AuthUserRole.GENERAL
            "developer" -> AuthUserRole.DEVELOPER
            "admin" -> AuthUserRole.ADMIN
            else -> throw RuntimeException("不明なロール: ${user.role}")
        }
        service.createUser(user.name, user.password, role)
    }
}

@Controller
@RequestMapping("/auth")
class AuthController(
    private val service: AuthUserService
) {
    @PostMapping("/new")
    fun createUser(
        @ModelAttribute user: NewUserDTO,
    ): String {
        println("${user.name}, ${user.password}, ${user.role}")
        val role: AuthUserRole = when (user.role) {
            "general" -> AuthUserRole.GENERAL
            "developer" -> AuthUserRole.DEVELOPER
            "admin" -> AuthUserRole.ADMIN
            else -> throw RuntimeException("不明なロール: ${user.role}")
        }
        service.createUser(user.name, user.password, role)
        return "redirect:/"
    }
}