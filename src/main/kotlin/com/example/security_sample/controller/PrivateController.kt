package com.example.security_sample.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/private")
@Controller
class PrivateController {
    @GetMapping
    fun getPrivatePage(): String {
        return "private/private_index"
    }

    @GetMapping("/admin")
    fun getAdminPage(): String {
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