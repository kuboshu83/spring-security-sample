package com.example.security_sample.user.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class LoginController {
    @GetMapping("/login")
    fun loginForm(): String {
        return "auth/login"
    }

    @GetMapping("/logout")
    fun logoutForm(): String {
        return "auth/logout"
    }
}
