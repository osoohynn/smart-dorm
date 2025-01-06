package com.example.kotlinjwt.domain.auth.repository

import com.example.kotlinjwt.global.security.jwt.config.JwtProperties
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit


@Repository
class RefreshTokenRepository(
    private val redisTemplate: RedisTemplate<String, String>,
    private val jwtProperties: JwtProperties
) {
    fun setRefreshToken(username: String, refreshToken: String) {
        redisTemplate.opsForValue()
            .set(username, refreshToken, jwtProperties.refreshExp, TimeUnit.MILLISECONDS)
    }

    fun getRefreshToken(username: String): String? {
        return redisTemplate.opsForValue().get(username)
    }

    fun existsRefreshToken(username: String): Boolean {
        return redisTemplate.hasKey(username)
    }
}