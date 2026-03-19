package com.example.security_sample.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/")
@Controller
class TopController {
    @GetMapping
    fun getTopPage(model: Model): String {
        model.addAttribute("name", "akira")
        return "public/public_index"
    }
}