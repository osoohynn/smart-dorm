package com.example.kotlinjwt

import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import com.example.kotlinjwt.domain.book.dto.request.CreateBookRequest
import com.example.kotlinjwt.domain.book.service.BookService
import com.example.kotlinjwt.global.exception.CustomException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class KotlinJwtApplicationTests {

    @Autowired
    private lateinit var bookService: BookService

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreateBookOverlap() {
        val existingRequest = CreateBookRequest(
            roomType = RoomType.LAUNDRY,
            number = 101,
            time = LocalDateTime.now().plusHours(1),
            expectedTime = 60
        )

        // 첫 번째 예약 성공
        bookService.createBook(existingRequest)

        val overlappingRequest = existingRequest.copy(
            time = existingRequest.time.plusMinutes(30) // 겹치는 시간
        )

        assertThrows<CustomException> {
            bookService.createBook(overlappingRequest)
        }
    }

    @Test
    fun testCreateBookNoOverlap() {
        val request = CreateBookRequest(
            roomType = RoomType.LAUNDRY,
            number = 101,
            time = LocalDateTime.now().plusHours(3), // 겹치지 않는 시간
            expectedTime = 60
        )

        assertDoesNotThrow {
            bookService.createBook(request)
        }
    }

}
