package com.example.kotlinjwt.domain.admin.post.controller

import com.example.kotlinjwt.domain.admin.post.service.AdminPostService
import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*


@Tag(name = "관리자 게시글", description = "관리자의 게시글 관리")
@RestController
@RequestMapping("/admin/posts")
class AdminPostController (
    private val adminPostService: AdminPostService,
){
    @Operation(summary = "안 플린 게시글 조회", description = "안 풀린 신고/요청을 조회합니다.")
    @GetMapping("")
    fun getUnsolvedPosts() : List<PostResponse> {
        return adminPostService.getUnsolvedPosts()
    }

    @Operation(summary = "게시글 문제 해결", description = "신고/요청을 해결 상태로 변경합니다.")
    @PatchMapping("/{postId}")
    fun solvePost(@PathVariable postId: Long) {
        adminPostService.solvePost(postId)
    }
}