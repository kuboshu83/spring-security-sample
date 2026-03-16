package com.example.security_sample.infrastructure

import com.example.security_sample.auth.AuthUser
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class AuthUserRepository(
    private val dao: AuthUserDao
) {
    fun save(user: AuthUser) {
        dao.create(
            AuthUserRecord(
                user.id, user.name, user.authority, user.password, true, user.createdAt
            )
        )
    }

    fun delete(userId: String) {
        dao.delete(userId)
    }

    fun findById(userId: String): AuthUser? {
        return dao.findById(userId)?.let { record ->
            AuthUser(
                record.id, record.name, record.authority, record.password, record.createdAt
            )
        }
    }
}

data class AuthUserRecord(
    val id: String,
    val name: String,
    val authority: String,
    val password: String,
    val enabled: Boolean,
    val createdAt: OffsetDateTime,
)

@Mapper
interface AuthUserDao {
    fun create(@Param("user") user: AuthUserRecord)
    fun delete(@Param("id") userId: String)
    fun findById(@Param("id") userId: String): AuthUserRecord?
}