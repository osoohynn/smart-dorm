package com.example.kotlinjwt.domain.door.dto.response

import com.example.kotlinjwt.domain.door.domain.entity.Door

data class DoorResponse(
    val id: Long,
    val location: String,
    val isOpened: Boolean,
) {
    companion object {
        fun of(door: Door) : DoorResponse {
            return DoorResponse(
                id = door.id!!,
                location = door.location,
                isOpened = door.isOpened
            )
        }
    }
}
