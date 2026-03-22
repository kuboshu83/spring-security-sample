package com.example.security_sample.user.domain

interface AuthUserRepository {
    fun save(user: UserRegistration): UserId
    fun findById(userId: UserId): AuthUser?
    fun delete(userId: UserId)
    fun findByName(name: UserName): AuthUser?
    fun findAllUser(): List<AuthUser>
}