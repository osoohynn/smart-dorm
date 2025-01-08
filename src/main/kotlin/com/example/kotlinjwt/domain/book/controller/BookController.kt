package com.example.kotlinjwt.domain.book.controller

import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import com.example.kotlinjwt.domain.book.dto.request.CreateBookRequest
import com.example.kotlinjwt.domain.book.dto.request.UpdateBookRequest
import com.example.kotlinjwt.domain.book.dto.response.BookResponse
import com.example.kotlinjwt.domain.book.service.BookService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Tag(name = "예약", description = "예약 API")
@RestController
@RequestMapping("/books")
class BookController (
    private val bookService: BookService
) {
    @Operation(summary = "예약하기", description = "예약하기입니다. LAUNDRY, STUDY, MEETING 중 하나를 예약합니다. expectedTime == 예상 걸릴 시간(분)")
    @PostMapping
    fun create(@RequestBody request: CreateBookRequest) {
        bookService.createBook(request)
    }

    @Operation(summary = "예약 조회", description = "예약 정보들을 조회합니다. LAUNDRY, STUDY, MEETING 으로 조회합니다.")
    @GetMapping
    fun getBooks(@RequestParam("type") type: RoomType) : List<BookResponse> {
        return bookService.getBooks(type)
    }

    @Operation(summary = "현재 진행 중인 예약 조회", description = "현재 진행되고 있는 예약들을 조회합니다. LAUNDRY, STUDY, MEETING 으로 조회합니다.")
    @GetMapping("/progress")
    fun getProgressBooks(@RequestParam("type") type: RoomType) : List<BookResponse> {
        return bookService.getProgressBooks(type)
    }

    @Operation(summary = "예약 완료하기", description = "예약 번호로 일정이 끝남을 표시합니다.")
    @PatchMapping("/finish/{bookId}")
    fun finishBook(@PathVariable bookId: Long) {
        bookService.finishBook(bookId)
    }

    @Operation(summary = "예약 수정하기", description = "예약 번호로 예약 내용을 수정합니다. 예약실(LAUNDRY, STUDY, MEETING)은 변경 불가능합니다.")
    @PatchMapping("/{bookId}")
    fun updateBook(@PathVariable bookId: Long, @RequestBody request: UpdateBookRequest) {
        bookService.updateBook(request, bookId)
    }

    @Operation(summary = "예약 삭제하기", description = "예약 번호로 예약을 삭제합니다.")
    @DeleteMapping("/{bookId}")
    fun deleteBook(@PathVariable bookId: Long) {
        bookService.deleteBook(bookId)
    }
}