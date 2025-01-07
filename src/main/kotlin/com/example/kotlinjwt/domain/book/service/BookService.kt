package com.example.kotlinjwt.domain.book.service

import com.example.kotlinjwt.domain.book.domain.entity.Book
import com.example.kotlinjwt.domain.book.dto.request.CreateBookRequest
import com.example.kotlinjwt.domain.book.error.BookError
import com.example.kotlinjwt.domain.book.repository.BookRepository
import com.example.kotlinjwt.global.exception.CustomException
import com.example.kotlinjwt.global.security.SecurityHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class BookService (
    private val bookRepository: BookRepository,
    private val securityHolder: SecurityHolder
) {
    @Transactional
    fun createBook(request: CreateBookRequest) {
        if (request.time.isBefore(LocalDateTime.now())) {
            throw CustomException(BookError.PASSED_TIME)
        }

        if (request.time.isAfter(request.finishTime)) {
            throw CustomException(BookError.FINISH_MUST_BE_AFTER_START)
        }

        val book = Book(
            roomType = request.roomType,
            roomNumber = request.roomNumber,
            itemNumber = request.itemNumber,
            time = request.time,
            finishTime = request.finishTime,
            bookedBy = securityHolder.user
        )

        bookRepository.save(book)
    }
}