package com.example.kotlinjwt.domain.post.dto.request

import com.example.kotlinjwt.domain.post.domain.enums.PostType
import org.springframework.web.multipart.MultipartFile

data class CreatePostRequest(
    val title: String,
    val content: String,
    val type: PostType,
    val location: String,
    val file: String
)
