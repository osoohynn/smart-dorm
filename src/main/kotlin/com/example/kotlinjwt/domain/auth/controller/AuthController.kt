package com.example.kotlinjwt.domain.auth.controller

import com.example.kotlinjwt.domain.auth.dto.request.LoginRequest
import com.example.kotlinjwt.domain.auth.dto.request.ReissueRequest
import com.example.kotlinjwt.domain.auth.dto.request.SignUpRequest
import com.example.kotlinjwt.domain.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "인증", description = "Auth")
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @Operation(summary = "회원가입", description = "회원가입합니다.")
    @PostMapping("/sign-up")
    fun signup(@RequestBody request: SignUpRequest) = authService.signup(request)

    @Operation(summary = "로그인", description = "로그인합니다.")
    @PostMapping("/sign-in")
    fun login(@RequestBody request: LoginRequest) = authService.login(request)

    @Operation(summary = "토큰 재발급", description = "리프레시 토큰으로 토큰을 재발급합니다.")
    @PostMapping("/reissue")
    fun reissue(@RequestBody request: ReissueRequest) = authService.reissue(request)
}