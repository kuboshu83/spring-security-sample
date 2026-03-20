package com.example.security_sample.auth.domain

enum class AuthUserRole(val code: String) {
    ADMIN("ADMIN"),
    GENERAL("GENERAL"),
    DEVELOPER("DEVELOPER");
}