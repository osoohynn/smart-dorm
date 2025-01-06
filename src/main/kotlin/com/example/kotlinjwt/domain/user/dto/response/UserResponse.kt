package com.example.kotlinjwt.domain.user.dto.response

import com.example.kotlinjwt.domain.user.domain.entity.User

data class UserResponse(
    val username: String,
) {
    companion object {
        fun of(user: User) = UserResponse(
            username = user.username,
        )
    }
}