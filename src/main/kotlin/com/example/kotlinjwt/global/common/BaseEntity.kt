package com.example.kotlinjwt.global.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Column(name = "created_at")
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())

    @Column(name = "modified_at")
    @LastModifiedDate
    var modifiedAt: LocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
}