package com.example.security_sample.user

import com.example.security_sample.user.domain.AuthUserRepository
import com.example.security_sample.user.domain.UserName
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetails(
    val id: String,
    name: String,
    password: String,
    authorities: Collection<GrantedAuthority>
) : User(name, password, authorities)

class PsqlUserDetailsService(
    private val repository: AuthUserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByName(UserName(username))
            ?: throw UsernameNotFoundException("ユーザが見つかりません: name=$username")
        val authorities = listOf<GrantedAuthority>(
            SimpleGrantedAuthority(user.role.code)
        )
        return CustomUserDetails(
            user.id.value, user.name.value, user.password.value, authorities
        )
    }
}