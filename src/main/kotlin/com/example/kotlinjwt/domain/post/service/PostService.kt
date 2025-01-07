package com.example.kotlinjwt.domain.post.service

import com.example.kotlinjwt.domain.image.service.ImageService
import com.example.kotlinjwt.domain.post.domain.entity.Post
import com.example.kotlinjwt.domain.post.dto.request.CreatePostRequest
import com.example.kotlinjwt.domain.post.dto.request.UpdatePostRequest
import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import com.example.kotlinjwt.domain.post.error.PostError
import com.example.kotlinjwt.domain.post.repository.PostRepository
import com.example.kotlinjwt.global.exception.CustomException
import com.example.kotlinjwt.global.security.SecurityHolder
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile


@Service
class PostService (
    private val postRepository: PostRepository,
    private val securityHolder: SecurityHolder,
    private val imageService: ImageService
){
    @Transactional
    fun createPost(request: CreatePostRequest, files: List<MultipartFile>) {
        val post = Post(
            title = request.title,
            content = request.content,
            type = request.type,
            isSolved = false,
            location = request.location,
            author = securityHolder.user
        )

        postRepository.save(post)

        if (files.size > 5) {
            throw CustomException(PostError.FILE_UPLOAD_FIVE)
        }

        for (file in files) {
            imageService.uploadImage(file, post.id!!)
        }
    }

    fun getPosts(): List<PostResponse> {
        val posts = postRepository.findAll()

        return posts.map { post -> PostResponse.of(post) }
    }

    fun getPost(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw CustomException(PostError.POST_NOT_FOUND)

        return PostResponse.of(post)
    }

    fun updatePost(request: UpdatePostRequest, postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw CustomException(PostError.POST_NOT_FOUND)

        post.title = request.title ?: post.title
        post.content = request.content ?: post.content
        post.location = request.location ?: post.location

        postRepository.save(post)
    }

    fun deletePost(postId: Long) {
        if (!postRepository.existsById(postId)) {
            throw CustomException(PostError.POST_NOT_FOUND)
        }
        postRepository.deleteById(postId)
    }
}