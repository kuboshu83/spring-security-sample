package com.example.security_sample.auth.web

import com.example.security_sample.auth.domain.UserRole
import com.example.security_sample.auth.exception.InputException
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
        val role = UserRole.from(user.role)
            ?: throw InputException.UnknownRole(user.role)
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
        if (user.name.isBlank()) {
            throw InputException.InvalidName(user.name)
        }
        val role = UserRole.from(user.role)
            ?: throw InputException.UnknownRole(user.role)
        service.createUser(user.name, user.password, role)
        logger.info("Create new user: name=${user.name} role=${user.role}")
        return "redirect:/"
    }
}