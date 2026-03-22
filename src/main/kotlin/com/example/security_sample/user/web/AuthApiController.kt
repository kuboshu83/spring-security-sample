package com.example.security_sample.user.web

import com.example.security_sample.user.domain.Password
import com.example.security_sample.user.domain.UserName
import com.example.security_sample.user.domain.UserRole
import com.example.security_sample.user.exception.InputException
import com.example.security_sample.user.service.AuthUserService
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

    private val logger = LoggerFactory.getLogger(ApiAuthController::class.simpleName)

    @PostMapping("/new")
    fun createUser(
        @RequestBody user: NewUserDTO,
    ) {
        logger.info("Create new user: name=${user.name} role=${user.role}")
        val role = UserRole.from(user.role)
            ?: throw InputException.UnknownRole(user.role)
        val name = UserName(user.name)
        val password = Password(user.password)
        service.createUser(name, password, role)
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
        val name = UserName(user.name)
        val password = Password(user.password)
        service.createUser(name, password, role)
        logger.info("Create new user: name=${user.name} role=${user.role}")
        return "redirect:/"
    }
}