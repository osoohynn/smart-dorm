package com.example.kotlinjwt.domain.user.service

import com.example.kotlinjwt.domain.user.dto.response.UserResponse
import com.example.kotlinjwt.global.security.SecurityHolder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val securityHolder: SecurityHolder
) {
    fun getMe() : UserResponse {
        val user = securityHolder.user

        return UserResponse.of(user)
    }
}