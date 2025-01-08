package com.example.kotlinjwt.domain.user.controller

import com.example.kotlinjwt.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "유저", description = "유저 API")
@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService
) {
    @Operation(summary = "유저 나 조회", description = "본인 정보를 조회합니다.")
    @GetMapping("/me")
    fun getMe() = userService.getMe()
}