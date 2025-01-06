package com.example.kotlinjwt.domain.post.domain.entity

import com.example.kotlinjwt.domain.post.domain.enums.PostType
import com.example.kotlinjwt.global.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "posts")
class PostEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,

    var content: String,

    var file: String,

    val type: PostType,

    var isSolved: Boolean,

    var location: String,
) : BaseEntity()