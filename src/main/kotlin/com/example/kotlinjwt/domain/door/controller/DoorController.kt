package com.example.kotlinjwt.domain.door.controller

import com.example.kotlinjwt.domain.door.dto.request.CreateDoorRequest
import com.example.kotlinjwt.domain.door.dto.response.DoorResponse
import com.example.kotlinjwt.domain.door.service.DoorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "문", description = "admin 문 관리 API")
@RestController
@RequestMapping("/doors")
class DoorController (
    private val doorService: DoorService
) {
    @Operation(summary = "문 등록", description = "위치를 입력하여 문을 등록합니다. 번호는 자동으로 생성됩니다.")
    @PostMapping
    fun createDoor(@RequestBody request: CreateDoorRequest): ResponseEntity<Void> {
        doorService.createDoor(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Operation(summary = "문 열기", description = "문 번호로 문을 엽니다.")
    @PostMapping("/{doorId}/open")
    fun openDoor(@PathVariable doorId: Long): ResponseEntity<Void> {
        doorService.openDoor(doorId)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "문 닫기", description = "문 번호로 문을 닫습니다.")
    @PostMapping("/{doorId}/close")
    fun closeDoor(@PathVariable doorId: Long): ResponseEntity<Void> {
        doorService.closeDoor(doorId)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "문 조회", description = "전체 문을 조회합니다.")
    @GetMapping
    fun getDoors(): ResponseEntity<List<DoorResponse>> {
        val doors = doorService.getDoors()
        return ResponseEntity.ok(doors)
    }

}