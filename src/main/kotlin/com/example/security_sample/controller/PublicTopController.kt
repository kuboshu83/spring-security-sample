package com.example.security_sample.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/")
@Controller
class PublicTopController {
    @GetMapping
    fun getPage(): String {
        return "public/public_index"
    }
}