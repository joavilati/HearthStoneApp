package com.example.hearthstoneapp.domain

sealed class Result {
    data class OnSuccess<T>(val data: T) : Result()
    class OnError(val message: String) : Result()
}