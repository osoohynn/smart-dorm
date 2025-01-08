package com.example.kotlinjwt.domain.door.dto.response

import com.example.kotlinjwt.domain.door.domain.entity.Door
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "번호, 위치, 열림 여부")
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
