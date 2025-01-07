package com.example.kotlinjwt.domain.post.dto.response

import com.example.kotlinjwt.domain.post.domain.entity.Post
import com.example.kotlinjwt.domain.post.domain.enums.PostType

data class PostResponse(
    val title: String,
    val content: String,
    val location: String,
    val type: PostType,
    val isSolved: Boolean
) {
    companion object {
        fun of(post: Post): PostResponse {
            return PostResponse(
                title = post.title,
                content = post.content,
                location = post.location,
                type = post.type,
                isSolved = post.isSolved
            )
        }
    }
}
