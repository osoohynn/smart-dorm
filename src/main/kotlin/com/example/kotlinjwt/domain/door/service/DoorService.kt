package com.example.kotlinjwt.domain.door.service

import com.example.kotlinjwt.domain.door.domain.entity.Door
import com.example.kotlinjwt.domain.door.dto.request.CreateDoorRequest
import com.example.kotlinjwt.domain.door.dto.response.DoorResponse
import com.example.kotlinjwt.domain.door.error.DoorError
import com.example.kotlinjwt.domain.door.repository.DoorRepository
import com.example.kotlinjwt.global.exception.CustomException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class DoorService (
    private val doorRepository: DoorRepository
) {
    fun createDoor(request: CreateDoorRequest) {
        val door = Door(
            location = request.location,
            isOpened = false
        )

        doorRepository.save(door)
    }

    fun openDoor(doorId: Long) {
        val door = doorRepository.findByIdOrNull(doorId) ?: throw CustomException(DoorError.DOOR_NOT_FOUND)

        door.isOpened = true

        doorRepository.save(door)
    }

    fun closeDoor(doorId: Long) {
        val door = doorRepository.findByIdOrNull(doorId) ?: throw CustomException(DoorError.DOOR_NOT_FOUND)

        door.isOpened = false

        doorRepository.save(door)
    }

    fun getDoors() : List<DoorResponse> {
        val doors = doorRepository.findAll()

        return doors.map { door -> DoorResponse.of(door) }
    }
}