package com.example.security_sample.config

import com.example.security_sample.auth.AuthUserRepository
import com.example.security_sample.auth.PostgreSqlUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { }
            authorizeHttpRequests {
                authorize("/private/admin/**", hasAuthority("ADMIN_ROLE"))
                authorize("/private/developer/**", hasAuthority("DEVELOPER_ROLE"))
                authorize("/private/general/**", hasAuthority("GENERAL_ROLE"))
                authorize("/private/**", authenticated)
                authorize(anyRequest, permitAll)
            }
            formLogin { }
        }
        return http.build()
    }

    @Bean
    fun userDetailsService(repo: AuthUserRepository): UserDetailsService {
        return PostgreSqlUserDetailsService(repo)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}