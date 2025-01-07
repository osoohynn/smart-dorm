package com.example.kotlinjwt.domain.post.controller

import com.example.kotlinjwt.domain.post.domain.enums.PostType
import com.example.kotlinjwt.domain.post.dto.request.CreatePostRequest
import com.example.kotlinjwt.domain.post.dto.request.UpdatePostRequest
import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import com.example.kotlinjwt.domain.post.service.PostService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/posts")
class PostController (
    private val postService: PostService
){
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPost(
        @RequestParam type: PostType,
        @RequestParam title: String,
        @RequestParam content: String,
        @RequestParam location: String,
        @RequestPart("files", required = false) files: List<MultipartFile>
    ) {
        val request = CreatePostRequest(type = type, title = title, content = content, location = location)
        postService.createPost(request, files)
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