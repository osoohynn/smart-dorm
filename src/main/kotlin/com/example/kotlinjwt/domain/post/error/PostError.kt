package com.example.kotlinjwt.domain.post.error

import com.example.kotlinjwt.global.exception.CustomError

enum class PostError (override val status: Int, override val message: String) : CustomError {
    POST_NOT_FOUND(404, "찾을 수 없는 게시글입니다"),
}