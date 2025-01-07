package com.example.kotlinjwt.domain.post.repository

import com.example.kotlinjwt.domain.post.domain.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByIsSolved(isSolved: Boolean): List<Post>
}