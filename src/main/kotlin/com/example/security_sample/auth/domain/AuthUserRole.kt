package com.example.security_sample.auth.domain

enum class AuthUserRole(val code: String) {
    ADMIN("ADMIN"),
    GENERAL("GENERAL"),
    DEVELOPER("DEVELOPER");

    companion object {
        fun from(role: String): AuthUserRole? {
            return entries.find { it.code == role }
        }
    }
}