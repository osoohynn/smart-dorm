package com.example.kotlinjwt.domain.post.service

import com.example.kotlinjwt.domain.post.domain.entity.PostEntity
import com.example.kotlinjwt.domain.post.dto.request.CreatePostRequest
import com.example.kotlinjwt.domain.post.repository.PostRepository
import org.springframework.stereotype.Service


@Service
class PostService (
    private val postRepository: PostRepository
){
    fun createPost(request: CreatePostRequest) {
        val post = PostEntity(
            title = request.title,
            content = request.content,
            file = "file",
            type = request.type,
            isSolved = false,
            location = request.location,
        )

        postRepository.save(post)
    }
}