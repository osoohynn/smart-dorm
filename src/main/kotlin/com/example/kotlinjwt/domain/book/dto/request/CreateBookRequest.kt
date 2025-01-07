package com.example.kotlinjwt.domain.book.dto.request

import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import java.time.LocalDateTime

data class CreateBookRequest(
    val roomType: RoomType,
    val time: LocalDateTime,
    val finishTime: LocalDateTime? = null,
    val roomNumber: Int? = null,
    val itemNumber: Int? = null
)
