package com.example.security_sample.auth.domain

import java.time.OffsetDateTime

enum class UserStatus(val status: String) {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    LOCKED("LOCKED");
}

data class AuthUser(
    val id: String,
    val name: String,
    val role: AuthUserRole,
    val password: String,
    val status: UserStatus,
    val createdAt: OffsetDateTime,
)