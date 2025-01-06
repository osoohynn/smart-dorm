package com.example.kotlinjwt.domain.post.repository

import com.example.kotlinjwt.domain.post.domain.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findAllByIsSolved(isSolved: Boolean): List<PostEntity>
}