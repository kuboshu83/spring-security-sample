package com.example.security_sample

import com.example.security_sample.auth.AuthUser
import com.example.security_sample.infrastructure.AuthUserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.OffsetDateTime

@SpringBootApplication
class SecuritySampleApplication

fun main(args: Array<String>) {
//    runApplication<SecuritySampleApplication>(*args)
    val context = runApplication<SecuritySampleApplication>(*args)
    val repo = context.getBean("authUserRepository") as AuthUserRepository
    val user = AuthUser(
        "akira_id", "akira", "root", "akira-pass", OffsetDateTime.now()
    )
    repo.save(user)
    println(repo.findById("akira_id"))
    repo.delete("akira_id")
    println(repo.findById("akira_id"))
}
