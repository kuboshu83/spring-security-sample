package com.example.security_sample.controller.private

import com.example.security_sample.auth.AuthUserService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/private")
@Controller
class PrivateController(
    private val userService: AuthUserService,
) {
    @GetMapping
    fun getPrivatePage(
        model: Model,
        authentication: Authentication,
    ): String {
        val name = authentication.name
        model.addAttribute("name", name)
        return "private/private_index"
    }

    @GetMapping("/admin")
    fun getAdminPage(model: Model): String {
        val users = userService.findAllUser()
        model.addAttribute("users", users)
        return "private/admin/admin_index"
    }

    @GetMapping("/developer")
    fun getDeveloperPage(): String {
        return "private/developer/developer_index"
    }

    @GetMapping("/general")
    fun getGeneralPage(): String {
        return "private/general/general_user_index"
    }
}