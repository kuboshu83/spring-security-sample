package com.example.security_sample.auth.service

import com.example.security_sample.auth.domain.AuthUser
import com.example.security_sample.auth.domain.AuthUserRepository
import com.example.security_sample.auth.domain.AuthUserRole
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.util.*

@Service
class AuthUserService(
    private val repo: AuthUserRepository,
    private val encoder: PasswordEncoder,
) {
    @Transactional
    fun createUser(name: String, rawPassword: String, role: AuthUserRole): AuthUser {
        val id = UUID.randomUUID().toString()
        val encodedPassword = encoder.encode(rawPassword) ?: throw RuntimeException("パスワードのエンコード失敗")
        val authUser = AuthUser(id, name, role, encodedPassword, true, OffsetDateTime.now())
        repo.save(authUser)
        return authUser
    }

    fun deleteUser(id: String): AuthUser {
        val user = repo.findById(id) ?: throw RuntimeException("ユーザが見つかりません: id=$id")
        repo.delete(id)
        return user
    }

    fun findUserById(id: String): AuthUser {
        return repo.findById(id) ?: throw RuntimeException("ユーザが見つかりません: id=$id")
    }

    fun findAllUser(): List<AuthUser> {
        val users = repo.findAllUser()
        if (users.isEmpty()) {
            throw RuntimeException("ユーザが見つかりません")
        }
        return users;
    }
}