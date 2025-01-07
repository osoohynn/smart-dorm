package com.example.kotlinjwt.domain.book.repository

import com.example.kotlinjwt.domain.book.domain.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface BookRepository : JpaRepository<Book, Long> {
    fun findByTimeBeforeAndIsFinishedFalse(time: LocalDateTime): List<Book>

    fun findByTimeAfter(time: LocalDateTime): List<Book>
}