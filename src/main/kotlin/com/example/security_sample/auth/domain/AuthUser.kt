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

@JvmInline
value class UserId(val value: String) {
    init {
        require(!value.isBlank())
    }
}

@JvmInline
value class UserName(val value: String) {
    init {
        require(!value.isBlank())
    }
}

@JvmInline
value class Password(val value: String) {
    init {
        require(!value.isBlank())
    }
}

data class AuthUser(
    val id: UserId,
    val name: UserName,
    val role: UserRole,
    val password: Password,
    val status: UserStatus,
    val createdAt: OffsetDateTime,
)