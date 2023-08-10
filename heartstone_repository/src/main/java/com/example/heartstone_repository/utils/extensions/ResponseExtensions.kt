package com.example.heartstone_repository.utils.extensions

import com.example.heartstone_repository.utils.*
import retrofit2.Response

fun <T> Response<T>.handleErrors(): T {
    when {
        isSuccessful -> {
            return body() ?: throw NullPointerException()
        }
        code() == 400 -> {
            throw BadRequestException()
        }
        code() == 401 -> {
            throw UnauthorizedException()
        }
        code() == 403 -> {
            throw ForbiddenException()
        }
        code() == 404 -> {
            throw NotFoundException()
        }
        code() == 500 -> {
            throw InternalServerErrorException()
        }
        else -> {
            throw Exception("Unknown error occurred")
        }
    }
}
