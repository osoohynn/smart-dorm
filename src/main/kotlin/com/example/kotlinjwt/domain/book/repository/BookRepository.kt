package com.example.kotlinjwt.domain.book.repository

import com.example.kotlinjwt.domain.book.domain.entity.Book
import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface BookRepository : JpaRepository<Book, Long> {
    fun findByTimeBeforeAndIsFinishedFalseAndRoomType(
        time: LocalDateTime,
        roomType: RoomType
    ): List<Book>

    fun findByTimeAfterAndRoomType(
        time: LocalDateTime,
        roomType: RoomType
    ): List<Book>

    fun existsByRoomTypeAndNumberAndTimeLessThanEqualAndEndTimeGreaterThanEqual(
        roomType: RoomType,
        number: Int,
        time: LocalDateTime,
        endTime: LocalDateTime
    ): Boolean

}