package com.example.security_sample.config

import com.example.security_sample.auth.PsqlUserDetailsService
import com.example.security_sample.auth.domain.AuthUserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { }
            authorizeHttpRequests {
                authorize("/private/admin/**", hasAuthority("ADMIN"))
                authorize("/private/developer/**", hasAuthority("DEVELOPER"))
                authorize("/private/general/**", hasAuthority("GENERAL"))
                authorize("/private/**", authenticated)
                authorize(anyRequest, permitAll)
            }
            formLogin { }
        }
        return http.build()
    }

    @Bean
    fun userDetailsService(repo: AuthUserRepository): UserDetailsService {
        return PsqlUserDetailsService(repo)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}