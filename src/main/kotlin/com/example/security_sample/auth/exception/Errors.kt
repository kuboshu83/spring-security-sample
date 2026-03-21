package com.example.security_sample.auth.exception

sealed class AuthException(message: String, cause: Throwable?) : RuntimeException(message, cause)

sealed class InputException(
    message: String,
    cause: Throwable? = null,
) : AuthException(message, cause) {
    class UnknownRole(
        val role: String
    ) : InputException("Unknown role: role='${role}'")

    class InvalidName(
        val name: String
    ) : InputException("Invalid name: name='${name}'")
}