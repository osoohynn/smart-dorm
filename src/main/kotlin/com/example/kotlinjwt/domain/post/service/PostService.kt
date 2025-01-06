package com.example.kotlinjwt.domain.post.service

import com.example.kotlinjwt.domain.post.domain.entity.PostEntity
import com.example.kotlinjwt.domain.post.dto.request.CreatePostRequest
import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import com.example.kotlinjwt.domain.post.error.PostError
import com.example.kotlinjwt.domain.post.repository.PostRepository
import com.example.kotlinjwt.domain.user.error.UserError
import com.example.kotlinjwt.global.exception.CustomException
import org.springframework.data.repository.findByIdOrNull
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

    fun getPosts(): List<PostResponse> {
        val posts = postRepository.findAll()

        return posts.map { post -> PostResponse.of(post) }
    }

    fun getPost(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw CustomException(PostError.POST_NOT_FOUND)

        return PostResponse.of(post)
    }
}