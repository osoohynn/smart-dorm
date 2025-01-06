package com.example.kotlinjwt.global.security

import com.example.kotlinjwt.domain.user.domain.entity.User
import com.example.kotlinjwt.domain.user.error.UserError
import com.example.kotlinjwt.domain.user.repository.UserRepository
import com.example.kotlinjwt.global.exception.CustomException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class SecurityHolder(
    private val userRepository: UserRepository,
) {
    val user: User
        get() = userRepository.findByUsername(SecurityContextHolder.getContext().authentication.name)
            ?: throw CustomException(UserError.USER_NOT_FOUND)
}