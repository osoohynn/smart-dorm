package com.example.kotlinjwt.domain.book.controller

import com.example.kotlinjwt.domain.book.dto.request.CreateBookRequest
import com.example.kotlinjwt.domain.book.dto.request.UpdateBookRequest
import com.example.kotlinjwt.domain.book.dto.response.BookResponse
import com.example.kotlinjwt.domain.book.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/books")
class BookController (
    private val bookService: BookService
) {
    @PostMapping
    fun create(@RequestBody request: CreateBookRequest) {
        bookService.createBook(request)
    }

    @GetMapping
    fun getBooks() : List<BookResponse> {
        return bookService.getBooks()
    }

    @GetMapping("/progress")
    fun getProgressBooks() : List<BookResponse> {
        return bookService.getProgressBooks()
    }

    @PatchMapping("/finish/{postId}")
    fun finishBook(@PathVariable postId: Long) {
        bookService.finishBook(postId)
    }

    @PatchMapping("/{postId}")
    fun updateBook(@PathVariable postId: Long, @RequestBody request: UpdateBookRequest) {
        return bookService.updateBook(request, postId)
    }
}