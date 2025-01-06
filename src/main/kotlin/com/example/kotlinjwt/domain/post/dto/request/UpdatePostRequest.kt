package com.example.kotlinjwt.domain.post.dto.request

data class UpdatePostRequest (
    val title: String ?= null,
    val content: String ?= null,
    val location: String ?= null,
) {
}