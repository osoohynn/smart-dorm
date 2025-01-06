package com.example.kotlinjwt.domain.post.controller

import com.example.kotlinjwt.domain.post.dto.request.CreatePostRequest
import com.example.kotlinjwt.domain.post.dto.request.UpdatePostRequest
import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import com.example.kotlinjwt.domain.post.service.PostService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/posts")
class PostController (
    private val postService: PostService
){
    @PostMapping
    fun createPost(@RequestBody request: CreatePostRequest) {
        postService.createPost(request)
    }

    @GetMapping
    fun getPosts() : List<PostResponse> {
        return postService.getPosts()
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long) : PostResponse {
        return postService.getPost(postId)
    }

    @PatchMapping("/{postId}")
    fun updatePost(@RequestBody request: UpdatePostRequest, @PathVariable postId: Long) {
        postService.updatePost(request, postId)
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long) {
        postService.deletePost(postId)
    }
}