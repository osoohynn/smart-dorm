package com.example.kotlinjwt.domain.image.controller

import com.example.kotlinjwt.domain.image.service.ImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val imageService: ImageService
) {
//    @PostMapping("/upload/{postId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
//    fun uploadImage(
//        @PathVariable postId: Long,
//        @RequestPart("file") file: MultipartFile): String {
//        return imageService.uploadImage(file, postId)
//    }
//
//    @GetMapping("/post/{postId}")
//    fun getImageUrls(@PathVariable postId: Long): List<String> {
//        return imageService.getImagesByPostId(postId)
//    }
}