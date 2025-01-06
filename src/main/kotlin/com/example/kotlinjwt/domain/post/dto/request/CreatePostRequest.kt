package com.example.kotlinjwt.domain.post.dto.request

import com.example.kotlinjwt.domain.post.domain.enums.PostType

data class CreatePostRequest(
    val title: String,
    val content: String,
    val type: PostType,
    val location: String,
)
