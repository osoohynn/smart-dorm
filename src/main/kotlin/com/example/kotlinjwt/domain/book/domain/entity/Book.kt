package com.example.kotlinjwt.domain.book.domain.entity

import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import com.example.kotlinjwt.global.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime


@Entity
class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val roomType: RoomType,

    var time: LocalDateTime,

    var finishTime: LocalDateTime ?= null,

    var roomNumber: Int ?= null,

    var itemNumber: Int ?= null,
) : BaseEntity()