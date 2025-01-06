package com.example.kotlinjwt.domain.image.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.util.UUID


@Service
class ImageService(
    @Value("\${spring.upload.dir}") private val uploadDir: String
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Throws(IOException::class)
    fun uploadImage(file: MultipartFile): String {
        val filename = "${UUID.randomUUID()}-${file.originalFilename}"

        val directory = File(uploadDir)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val targetFile = File(directory, filename)
        file.transferTo(targetFile)

        log.info("File uploaded successfully: $filename")

        return filename
    }
}