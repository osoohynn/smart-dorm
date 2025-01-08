package com.example.kotlinjwt.domain.user.dto.response

import com.example.kotlinjwt.domain.user.domain.entity.User
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "유저 정보를 반환합니다.")
data class UserResponse(
    val username: String,
) {
    companion object {
        fun of(user: User) = UserResponse(
            username = user.username,
        )
    }
}