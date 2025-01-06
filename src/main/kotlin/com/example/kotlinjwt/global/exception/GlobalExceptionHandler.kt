package com.example.kotlinjwt.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleException(e: CustomException) = ErrorResponse.of(e)

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = "Unexpected error occurred: ${e.message}",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            code = e.javaClass.name
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}