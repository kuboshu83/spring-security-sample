package com.example.security_sample.auth.infrastructure

import com.example.security_sample.auth.domain.*
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class AuthUserRepositoryImpl(
    private val dao: AuthUserDao
) : AuthUserRepository {
    override fun save(user: UserRegistration): UserId {
        dao.create(UserRegistrationRecord.from(user))
        return user.id
    }

    override fun delete(userId: UserId) {
        dao.delete(userId.value)
    }

    override fun findById(userId: UserId): AuthUser? {
        return dao.findById(userId.value)?.toDomain()
    }

    override fun findByName(name: UserName): AuthUser? {
        return dao.findByName(name.value)?.toDomain()
    }

    override fun findAllUser(): List<AuthUser> {
        return dao.findAllUser().map { record ->
            record.toDomain()
        }
    }
}

data class UserRegistrationRecord(
    val id: String,
    val name: String,
    val role: String,
    val password: String,
    val status: String,
) {
    companion object {
        fun from(user: UserRegistration): UserRegistrationRecord {
            return UserRegistrationRecord(
                user.id.value,
                user.name.value,
                user.role.code,
                user.password.value,
                user.status.status
            )
        }
    }
}

data class AuthUserRecord(
    val id: String,
    val name: String,
    val role: String,
    val password: String,
    val status: String,
    val createdAt: OffsetDateTime,
) {
    fun toDomain(): AuthUser {
        return AuthUser(
            UserId(id),
            UserName(name),
            UserRole.valueOf(role),
            Password(password),
            UserStatus.valueOf(status),
            createdAt
        )
    }
}

@Mapper
interface AuthUserDao {
    fun create(@Param("user") user: UserRegistrationRecord)
    fun delete(@Param("id") userId: String)
    fun findById(@Param("id") userId: String): AuthUserRecord?
    fun findByName(@Param("name") name: String): AuthUserRecord?
    fun findAllUser(): List<AuthUserRecord>
}