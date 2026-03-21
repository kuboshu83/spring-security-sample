package com.example.security_sample.auth.domain

interface AuthUserRepository {
    fun save(user: AuthUser)
    fun findById(userId: String): AuthUser?
    fun delete(userId: String)
    fun findByName(name: String): AuthUser?
    fun findAllUser(): List<AuthUser>
}