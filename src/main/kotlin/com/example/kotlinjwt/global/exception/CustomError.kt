package com.example.kotlinjwt.global.exception

interface CustomError {
    val status: Int
    val message: String
}