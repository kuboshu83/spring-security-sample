package com.example.security_sample.auth.domain

import java.time.OffsetDateTime

enum class UserRole(val code: String) {
    ADMIN("ADMIN"),
    GENERAL("GENERAL"),
    DEVELOPER("DEVELOPER");

    companion object {
        fun from(role: String): UserRole? {
            return entries.find { it.code == role }
        }
    }
}

enum class UserStatus(val status: String) {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    LOCKED("LOCKED");
}

data class AuthUser(
    val id: String,
    val name: String,
    val role: UserRole,
    val password: String,
    val status: UserStatus,
    val createdAt: OffsetDateTime,
)