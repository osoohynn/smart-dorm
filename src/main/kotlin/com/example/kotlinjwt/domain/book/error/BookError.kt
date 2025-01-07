package com.example.kotlinjwt.domain.book.error

import com.example.kotlinjwt.global.exception.CustomError

enum class BookError (override val status: Int, override val message: String) : CustomError {
    PASSED_TIME(400, "이미 지난 시간입니다."),
    FINISH_MUST_BE_AFTER_START(400, "끝나는 시간이 시작 시간보다 빠릅니다."),
    TIME_UNAVAILABLE(400, "사용할 수 없는 시간입니다."),
}