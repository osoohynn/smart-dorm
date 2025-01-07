package com.example.kotlinjwt.domain.book.dto.response

import com.example.kotlinjwt.domain.book.domain.entity.Book
import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import java.time.LocalDateTime

data class BookResponse(
    val roomType: RoomType,
    val time: LocalDateTime,
    val expectedTime: Int? = null,
    val roomNumber: Int? = null,
    val itemNumber: Int? = null
) {
    companion object {
        fun of(book: Book): BookResponse {
            return BookResponse(
                roomType = book.roomType,
                time = book.time,
                expectedTime = book.expectedTime,
                roomNumber = book.roomNumber,
                itemNumber = book.itemNumber
            )
        }
    }
}
