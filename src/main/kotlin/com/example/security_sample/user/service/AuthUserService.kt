package com.example.security_sample.user.service

import com.example.security_sample.user.domain.*
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthUserService(
    private val repo: AuthUserRepository,
    private val encoder: PasswordEncoder,
) {
    @Transactional
    fun createGeneralUser(name: UserName, rawPassword: Password): AuthUser {
        val encodedPassword = encoder.encode(rawPassword.value) ?: throw RuntimeException("パスワードのエンコード失敗")
        val newUser = UserRegistration.createGeneralUser(name, Password(encodedPassword))
        val userId = repo.save(newUser)
        val user = repo.findById(userId) ?: throw java.lang.RuntimeException("ユーザが見つかりません")
        return user
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    fun createUser(name: UserName, rawPassword: Password, role: UserRole): UserId {
        val encodedPassword = encoder.encode(rawPassword.value) ?: throw RuntimeException("パスワードのエンコード失敗")
        val newUser = UserRegistration.createActiveUser(name, role, Password(encodedPassword))
        return repo.save(newUser)
    }

    fun deleteUser(id: UserId): AuthUser {
        val user = repo.findById(id) ?: throw RuntimeException("ユーザが見つかりません: id=$id")
        repo.delete(id)
        return user
    }

    fun findUserById(id: UserId): AuthUser {
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