package com.example.kotlinjwt.domain.image.repository

import com.example.kotlinjwt.domain.image.domain.ImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<ImageEntity, Long> {
    fun findAllByPostId(postId: Long): List<ImageEntity>
}