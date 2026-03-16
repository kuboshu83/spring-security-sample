package com.example.security_sample.auth

interface AuthUserRepository {
    fun save(user: AuthUser)
    fun findById(userId: String): AuthUser?
    fun delete(userId: String)
    fun findByName(name: String): AuthUser?
}