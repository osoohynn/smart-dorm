package com.example.kotlinjwt.domain.auth.dto.request

data class LoginRequest(
    val username: String,
    val password: String
)