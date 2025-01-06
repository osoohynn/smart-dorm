package com.example.kotlinjwt.domain.image.service

import com.example.kotlinjwt.domain.image.domain.ImageEntity
import com.example.kotlinjwt.domain.image.repository.ImageRepository
import com.example.kotlinjwt.domain.post.error.PostError
import com.example.kotlinjwt.domain.post.repository.PostRepository
import com.example.kotlinjwt.global.exception.CustomException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.io.IOException
import java.util.UUID


@Service
class ImageService(
    @Value("\${spring.upload.dir}") private val uploadDir: String,
    private val imageRepository: ImageRepository,
    private val postRepository: PostRepository,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun uploadImage(file: MultipartFile, postId: Long): String {
        val filename = "${UUID.randomUUID()}-${file.originalFilename}"

        val directory = File(uploadDir)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val targetFile = File(directory, filename)
        file.transferTo(targetFile)

        log.info("File uploaded successfully: $filename")

        val post = postRepository.findByIdOrNull(postId) ?: throw CustomException(PostError.POST_NOT_FOUND)

        val image = ImageEntity(filePath = filename, post = post)
        imageRepository.save(image)

        return filename
    }

    fun getImagesByPostId(postId: Long): List<String> {
        val images = imageRepository.findAllByPostId(postId)

        if (images.isEmpty()) {
            throw IllegalArgumentException("No images found for Post with ID $postId")
        }

        return images.map { image ->
            ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(image.filePath)
                .toUriString()
        }
    }
}