package com.example.kotlinjwt.domain.user.controller

import com.example.kotlinjwt.domain.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService
) {
    @GetMapping("/me")
    fun getMe() = userService.getMe()
}