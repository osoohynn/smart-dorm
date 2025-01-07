package com.example.kotlinjwt.domain.book.error

import com.example.kotlinjwt.global.exception.CustomError

enum class BookError (override val status: Int, override val message: String) : CustomError {
    PASSED_TIME(400, "이미 지난 시간입니다."),
    TIME_UNAVAILABLE(400, "이미 예약된 시간입니다."),
    BOOK_NOT_FOUND(404, "찾을 수 없는 예약입니다.")
}