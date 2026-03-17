package com.example.security_sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecuritySampleApplication

fun main(args: Array<String>) {
    runApplication<SecuritySampleApplication>(*args)
//    val context = runApplication<SecuritySampleApplication>(*args)
//    val repo = context.getBean("authUserRepository") as AuthUserRepository
//    val user = AuthUser(
//        "akira_id", "akira", "root", "akira-pass", OffsetDateTime.now()
//    )
//    repo.save(user)
//    println(repo.findById("akira_id"))
//    repo.delete("akira_id")
//    println(repo.findById("akira_id"))
}
