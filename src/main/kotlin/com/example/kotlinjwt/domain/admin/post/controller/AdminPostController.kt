package com.example.kotlinjwt.domain.admin.post.controller

import com.example.kotlinjwt.domain.admin.post.service.AdminPostService
import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/admin")
class AdminPostController (
    private val adminPostService: AdminPostService,
){
    @GetMapping("/posts")
    fun getUnsolvedPosts() : List<PostResponse> {
        return adminPostService.getUnsolvedPosts()
    }

    @PostMapping("/posts/{postId}")
    fun solvePost(@PathVariable postId: Long) {
        adminPostService.solvePost(postId)
    }
}