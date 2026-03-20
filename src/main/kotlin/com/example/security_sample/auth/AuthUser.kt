package com.example.security_sample.auth

import java.time.OffsetDateTime

data class AuthUser(
    val id: String,
    val name: String,
    val role: String,
    val password: String,
    val enable: Boolean,
    val createdAt: OffsetDateTime,
)

enum class Role {
    ADMIN_ROLE,
    GENERAL_ROLE,
    DEVELOPER_ROLE,
}