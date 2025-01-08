package com.example.kotlinjwt.domain.post.controller

import com.example.kotlinjwt.domain.post.domain.enums.PostType
import com.example.kotlinjwt.domain.post.dto.request.CreatePostRequest
import com.example.kotlinjwt.domain.post.dto.request.UpdatePostRequest
import com.example.kotlinjwt.domain.post.dto.response.PostResponse
import com.example.kotlinjwt.domain.post.service.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@Tag(name = "게시글", description = "신고/요청 게시글 API")
@RestController
@RequestMapping("/posts")
class PostController (
    private val postService: PostService
){
    @Operation(summary = "게시글 작성", description = "신고 또는 요청 게시글을 작성합니다. 파일은 최대 5개까지 업로드할 수 있습니다.")
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

    @Operation(summary = "게시글 전체 조회", description = "전체 게시글을 조회합니다.")
    @GetMapping
    fun getPosts() : List<PostResponse> {
        return postService.getPosts()
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글을 상세 조회합니다.")
    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long) : PostResponse {
        return postService.getPost(postId)
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PatchMapping("/{postId}")
    fun updatePost(@RequestBody request: UpdatePostRequest, @PathVariable postId: Long) {
        postService.updatePost(request, postId)
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long) {
        postService.deletePost(postId)
    }
}