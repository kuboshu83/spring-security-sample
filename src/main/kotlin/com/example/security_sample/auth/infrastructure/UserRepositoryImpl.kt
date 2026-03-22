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
    override fun save(user: AuthUser) {
        dao.create(
            AuthUserRecord(
                user.id.value,
                user.name.value,
                user.role.code,
                user.password.value,
                user.status.status,
                user.createdAt
            )
        )
    }

    override fun delete(userId: String) {
        dao.delete(userId)
    }

    override fun findById(userId: String): AuthUser? {
        return dao.findById(userId)?.let { record ->
            AuthUser(
                UserId(record.id),
                UserName(record.name),
                UserRole.valueOf(record.role),
                Password(record.password),
                UserStatus.valueOf(record.status),
                record.createdAt
            )
        }
    }

    override fun findByName(name: String): AuthUser? {
        return dao.findByName(name)?.let { record ->
            AuthUser(
                UserId(record.id),
                UserName(record.name),
                UserRole.valueOf(record.role),
                Password(record.password),
                UserStatus.valueOf(record.status),
                record.createdAt
            )
        }
    }

    override fun findAllUser(): List<AuthUser> {
        return dao.findAllUser().map { record ->
            AuthUser(
                UserId(record.id),
                UserName(record.name),
                UserRole.valueOf(record.role),
                Password(record.password),
                UserStatus.valueOf(record.status),
                record.createdAt
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
)

@Mapper
interface AuthUserDao {
    fun create(@Param("user") user: AuthUserRecord)
    fun delete(@Param("id") userId: String)
    fun findById(@Param("id") userId: String): AuthUserRecord?
    fun findByName(@Param("name") name: String): AuthUserRecord?
    fun findAllUser(): List<AuthUserRecord>
}