package com.example.kotlinjwt.domain.post.controller

import com.example.kotlinjwt.domain.post.dto.request.CreatePostRequest
import com.example.kotlinjwt.domain.post.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/posts")
class PostController (
    private val postService: PostService
){
    @PostMapping
    fun createPost(@RequestBody request: CreatePostRequest) {
        postService.createPost(request)
    }
}