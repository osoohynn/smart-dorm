package com.example.kotlinjwt.domain.book.domain.entity

import com.example.kotlinjwt.domain.book.domain.enums.RoomType
import com.example.kotlinjwt.domain.user.domain.entity.User
import com.example.kotlinjwt.global.common.BaseEntity
import jakarta.persistence.*
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var bookedBy: User
) : BaseEntity()