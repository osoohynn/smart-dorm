package com.example.kotlinjwt.global.exception

class CustomException(val error: CustomError, vararg args: String) : RuntimeException() {
    val code = (error as Enum<*>).name
    val status = error.status
    override val message = error.message.format(*args)
}