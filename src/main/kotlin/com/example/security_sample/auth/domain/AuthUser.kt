package com.example.security_sample.auth.domain

import java.time.OffsetDateTime

data class AuthUser(
    val id: String,
    val name: String,
    val role: AuthUserRole,
    val password: String,
    val enable: Boolean,
    val createdAt: OffsetDateTime,
)