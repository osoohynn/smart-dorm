package com.example.kotlinjwt.domain.post.error

import com.example.kotlinjwt.global.exception.CustomError

enum class PostError (override val status: Int, override val message: String) : CustomError {
    POST_NOT_FOUND(404, "찾을 수 없는 게시글입니다"),
    FILE_UPLOAD_FIVE(400, "파일은 최대 5개까지 업로드할 수 있습니다.")
}