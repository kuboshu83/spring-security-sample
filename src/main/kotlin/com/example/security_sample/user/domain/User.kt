package com.example.security_sample.user.domain

import java.time.OffsetDateTime
import java.util.*

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
value class UserId(val value: UUID)

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

class UserRegistration private constructor(
    val id: UserId,
    val name: UserName,
    val role: UserRole,
    val password: Password,
    val status: UserStatus,
) {
    companion object {
        fun createActiveUser(name: UserName, role: UserRole, password: Password): UserRegistration {
            val id = UserId(UUID.randomUUID())
            return UserRegistration(id, name, role, password, UserStatus.ACTIVE)
        }

        fun createGeneralUser(name: UserName, password: Password): UserRegistration {
            return createActiveUser(name, UserRole.GENERAL, password)
        }
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