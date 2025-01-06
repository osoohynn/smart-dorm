package com.example.kotlinjwt.domain.user.error

import com.example.kotlinjwt.global.exception.CustomError

enum class UserError(override val status: Int, override val message: String) : CustomError {
    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(400, "비밀번호가 일치하지 않습니다."),
    USERNAME_DUPLICATED(400, "이미 존재하는 유저네임입니다."),
}