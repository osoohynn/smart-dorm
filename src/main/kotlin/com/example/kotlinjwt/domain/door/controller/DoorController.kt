package com.example.kotlinjwt.domain.door.controller

import com.example.kotlinjwt.domain.door.dto.response.DoorResponse
import com.example.kotlinjwt.domain.door.service.DoorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "문", description = "admin 문 관리 API")
@RestController
@RequestMapping("/doors")
class DoorController (
    private val doorService: DoorService
) {
    @Operation(summary = "문 조회", description = "전체 문을 조회합니다.")
    @GetMapping
    fun getDoors(): ResponseEntity<List<DoorResponse>> {
        val doors = doorService.getDoors()
        return ResponseEntity.ok(doors)
    }
}