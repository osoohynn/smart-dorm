package com.example.kotlinjwt.domain.book.dto.response

import com.example.kotlinjwt.domain.book.domain.entity.Book
import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import java.time.LocalDateTime

data class BookResponse(
    val roomType: RoomType,
    val time: LocalDateTime,
    val endTime: LocalDateTime,
    val number: Int
) {
    companion object {
        fun of(book: Book): BookResponse {
            return BookResponse(
                roomType = book.roomType,
                time = book.time,
                endTime = book.endTime,
                number = book.number,
            )
        }
    }
}
