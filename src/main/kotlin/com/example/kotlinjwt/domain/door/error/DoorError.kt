package com.example.kotlinjwt.domain.door.error

import com.example.kotlinjwt.global.exception.CustomError

enum class DoorError (override val status: Int, override val message: String) : CustomError {
    DOOR_NOT_FOUND(404, "찾을 수 없는 문입니다.")
}