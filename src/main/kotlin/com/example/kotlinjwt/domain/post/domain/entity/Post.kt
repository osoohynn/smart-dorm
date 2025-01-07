package com.example.kotlinjwt.domain.post.domain.entity

import com.example.kotlinjwt.domain.post.domain.enums.PostType
import com.example.kotlinjwt.global.common.BaseEntity
import jakarta.persistence.*


@Entity
@Table(name = "posts")
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,

    var content: String,

    val type: PostType,

    var isSolved: Boolean,

    var location: String,
) : BaseEntity()