package com.example.kotlinjwt.domain.image.repository

import com.example.kotlinjwt.domain.image.domain.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long> {
    fun findAllByPostId(postId: Long): List<Image>
}