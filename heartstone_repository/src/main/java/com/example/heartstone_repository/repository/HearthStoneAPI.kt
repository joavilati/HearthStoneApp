package com.example.heartstone_repository.repository

import com.example.heartstone_repository.model.InfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface HearthStoneAPI {

    @GET("info")
    suspend fun getInfo(): Response<InfoResponse>
}