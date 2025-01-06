package com.example.kotlinjwt.domain.admin.post.service

import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import com.example.kotlinjwt.domain.post.error.PostError
import com.example.kotlinjwt.domain.post.repository.PostRepository
import com.example.kotlinjwt.global.exception.CustomException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class AdminPostService (
    private val postRepository: PostRepository
) {
    fun solvePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw CustomException(PostError.POST_NOT_FOUND)

        post.isSolved = true

        postRepository.save(post)
    }

    fun deletePost(postId: Long) {
        if (!postRepository.existsById(postId)) {
            throw CustomException(PostError.POST_NOT_FOUND)
        }
        postRepository.deleteById(postId)
    }

    fun getUnsolvedPosts() : List<PostResponse> {
        val posts = postRepository.findAllByIsSolved(false)

        return posts.map { post -> PostResponse.of(post) }
    }
}