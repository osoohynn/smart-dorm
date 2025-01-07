package com.example.kotlinjwt.domain.door.controller

import com.example.kotlinjwt.domain.door.dto.request.CreateDoorRequest
import com.example.kotlinjwt.domain.door.dto.response.DoorResponse
import com.example.kotlinjwt.domain.door.service.DoorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/doors")
class DoorController (
    private val doorService: DoorService
) {
    @PostMapping
    fun createDoor(@RequestBody request: CreateDoorRequest): ResponseEntity<Void> {
        doorService.createDoor(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/{doorId}/open")
    fun openDoor(@PathVariable doorId: Long): ResponseEntity<Void> {
        doorService.openDoor(doorId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{doorId}/close")
    fun closeDoor(@PathVariable doorId: Long): ResponseEntity<Void> {
        doorService.closeDoor(doorId)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getDoors(): ResponseEntity<List<DoorResponse>> {
        val doors = doorService.getDoors()
        return ResponseEntity.ok(doors)
    }

}