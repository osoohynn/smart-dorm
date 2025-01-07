package com.example.kotlinjwt.domain.book.controller

import com.example.kotlinjwt.domain.book.dto.request.CreateBookRequest
import com.example.kotlinjwt.domain.book.service.BookService
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
}