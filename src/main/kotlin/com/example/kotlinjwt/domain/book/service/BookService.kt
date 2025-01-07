package com.example.kotlinjwt.domain.book.service

import com.example.kotlinjwt.domain.book.domain.entity.Book
import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import com.example.kotlinjwt.domain.book.dto.request.CreateBookRequest
import com.example.kotlinjwt.domain.book.dto.request.UpdateBookRequest
import com.example.kotlinjwt.domain.book.dto.response.BookResponse
import com.example.kotlinjwt.domain.book.error.BookError
import com.example.kotlinjwt.domain.book.repository.BookRepository
import com.example.kotlinjwt.global.exception.CustomException
import com.example.kotlinjwt.global.security.SecurityHolder
import org.springframework.data.repository.findByIdOrNull
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

        if (checkBook(request.roomType, request.time, request.expectedTime, request.number)) {
            throw CustomException(BookError.TIME_UNAVAILABLE)
        }

        val book = Book(
            roomType = request.roomType,
            number = request.number,
            time = request.time,
            endTime = request.time.plusMinutes(request.expectedTime.toLong()),
            expectedTime = request.expectedTime,
            bookedBy = securityHolder.user,
            isFinished = false
        )

        bookRepository.save(book)
    }

    @Transactional
    fun getBooks(type: RoomType): List<BookResponse> {
        val books = bookRepository.findByTimeAfterAndRoomType(LocalDateTime.now(), type)

        return books.map { book -> BookResponse.of(book) }
    }

    @Transactional
    fun getProgressBooks(type: RoomType): List<BookResponse> {
        val books = bookRepository.findByTimeBeforeAndIsFinishedFalseAndRoomType(LocalDateTime.now(), type)

        return books.map { book -> BookResponse.of(book) }
    }

    @Transactional
    fun finishBook(bookId: Long) {
        val book = bookRepository.findByIdOrNull(bookId) ?: throw CustomException(BookError.BOOK_NOT_FOUND)

        book.isFinished = true

        bookRepository.save(book)
    }

    @Transactional
    fun updateBook(request: UpdateBookRequest, bookId: Long) {
        val book = bookRepository.findByIdOrNull(bookId) ?: throw CustomException(BookError.BOOK_NOT_FOUND)

        if (request.time != null && request.time.isBefore(LocalDateTime.now())) {
            throw CustomException(BookError.PASSED_TIME)
        }

        if (checkBook(book.roomType,
                request.time?: book.time,
                request.expectedTime?: book.expectedTime,
                request.number?: book.number)) {
            throw CustomException(BookError.TIME_UNAVAILABLE)
        }

        book.time = request.time ?: book.time
        book.number = request.number ?: book.number
        book.expectedTime = request.expectedTime ?: book.expectedTime
        if (request.expectedTime != null) {
            book.endTime = book.time.plusMinutes(request.expectedTime.toLong()) ?: book.endTime
        }

        bookRepository.save(book)
    }

    fun deleteBook(bookId: Long) {
        if (!bookRepository.existsById(bookId)) {
            throw CustomException(BookError.BOOK_NOT_FOUND)
        }

        bookRepository.deleteById(bookId)
    }

    fun checkBook(type: RoomType, time: LocalDateTime, expectedTime: Int, number: Int): Boolean {
        val requestEndTime = time.plusMinutes(expectedTime.toLong())

        val isOverlapping = bookRepository.existsByRoomTypeAndNumberAndTimeLessThanEqualAndEndTimeGreaterThanEqual(
            roomType = type,
            number = number,
            time = requestEndTime,
            endTime = time,
        )

        return isOverlapping
    }
}