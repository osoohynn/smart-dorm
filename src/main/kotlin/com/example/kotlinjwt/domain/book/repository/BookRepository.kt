package com.example.kotlinjwt.domain.book.repository

import com.example.kotlinjwt.domain.book.domain.entity.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
}