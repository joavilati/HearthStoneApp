package com.example.heartstone_repository.repository

import com.example.heartstone_repository.model.CardsByItemResponse
import com.example.heartstone_repository.model.InfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HearthStoneAPI {

    @GET("info")
    suspend fun getInfo(): Response<InfoResponse>

    @GET("cards/{typeName}/{name}")
    suspend fun getCardsBy(
        @Path("typeName") typeName: String,
        @Path("name") name: String,
    ):Response<List<CardsByItemResponse>>
}