package com.example.kotlinjwt.domain.book.dto.request

import java.time.LocalDateTime

data class UpdateBookRequest (
    val time: LocalDateTime? = null,
    val expectedTime: Int? = null,
    val roomNumber: Int? = null,
    val itemNumber: Int? = null
)