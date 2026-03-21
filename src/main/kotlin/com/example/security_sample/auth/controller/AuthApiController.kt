package com.example.security_sample.auth.controller

import com.example.security_sample.auth.domain.AuthUserRole
import com.example.security_sample.auth.service.AuthUserService
import org.slf4j.LoggerFactory
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
        val role = AuthUserRole.from(user.role)
            ?: throw RuntimeException("不明なロール: ${user.role}")
        service.createUser(user.name, user.password, role)
    }
}

@Controller
@RequestMapping("/auth")
class AuthController(
    private val service: AuthUserService
) {
    private val logger = LoggerFactory.getLogger(AuthController::class.simpleName)

    @PostMapping("/new")
    fun createUser(
        @ModelAttribute user: NewUserDTO,
    ): String {
        logger.info("Create new user: name=${user.name} role=${user.role}")
        val role = AuthUserRole.from(user.role)
            ?: throw RuntimeException("不明なロール: ${user.role}")
        service.createUser(user.name, user.password, role)
        return "redirect:/"
    }
}