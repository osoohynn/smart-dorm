package com.example.kotlinjwt.domain.auth.service

import com.example.kotlinjwt.domain.auth.dto.request.LoginRequest
import com.example.kotlinjwt.domain.auth.dto.request.ReissueRequest
import com.example.kotlinjwt.domain.auth.dto.request.SignUpRequest
import com.example.kotlinjwt.domain.auth.repository.RefreshTokenRepository
import com.example.kotlinjwt.domain.user.domain.entity.User
import com.example.kotlinjwt.domain.user.domain.enums.UserRole
import com.example.kotlinjwt.domain.user.error.UserError
import com.example.kotlinjwt.domain.user.repository.UserRepository
import com.example.kotlinjwt.global.exception.CustomException
import com.example.kotlinjwt.global.security.jwt.dto.JwtResponse
import com.example.kotlinjwt.global.security.jwt.enums.JwtType
import com.example.kotlinjwt.global.security.jwt.error.JwtError
import com.example.kotlinjwt.global.security.jwt.provider.JwtProvider
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
) {
    @Transactional
    fun signup(request: SignUpRequest) {
        val username = request.username
        val password = passwordEncoder.encode(request.password)

        if (userRepository.existsByUsername(username)) {
            throw CustomException(UserError.USERNAME_DUPLICATED)
        }

        val user = User(
            username = username,
            password = password,
            role = UserRole.USER
        )

        userRepository.save(user)
    }

    @Transactional
    fun login(request: LoginRequest): JwtResponse {
        val username = request.username
        val password = request.password

        val user = userRepository.findByUsername(username) ?: throw CustomException(UserError.USER_NOT_FOUND)

        if (!passwordEncoder.matches(password, user.password)) {
            throw CustomException(UserError.PASSWORD_NOT_MATCH)
        }

        return jwtProvider.generateToken(user)
    }

    @Transactional
    fun reissue(request: ReissueRequest): JwtResponse {
        if (jwtProvider.getType(request.refreshToken) != JwtType.REFRESH) {
            throw CustomException(JwtError.INVALID_TOKEN_TYPE)
        }

        val username = jwtProvider.getUsername(request.refreshToken)
        val user = userRepository.findByUsername(username) ?: throw CustomException(UserError.USER_NOT_FOUND)
        val refreshToken =
            refreshTokenRepository.getRefreshToken(username) ?: throw CustomException(JwtError.INVALID_TOKEN)

        if (refreshToken != request.refreshToken) {
            throw CustomException(JwtError.INVALID_TOKEN)
        }

        return jwtProvider.generateToken(user)
    }
}