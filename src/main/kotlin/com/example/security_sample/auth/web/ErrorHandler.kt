package com.example.security_sample.auth.web

import com.example.security_sample.auth.exception.InputException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class AuthErrorHandler {
    private val logger = LoggerFactory.getLogger(AuthErrorHandler::class.simpleName)

    @ExceptionHandler(InputException::class)
    fun handleInputException(ex: InputException): ResponseEntity<String> {
        logger.error(ex.message)
        return ResponseEntity.badRequest().body("入力値エラー")
    }
}