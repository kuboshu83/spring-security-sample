package com.example.security_sample.auth

import java.time.OffsetDateTime

data class AuthUser(
    val id: String,
    val name: String,
    val authority: String,
    val password: String,
    val createdAt: OffsetDateTime,
)