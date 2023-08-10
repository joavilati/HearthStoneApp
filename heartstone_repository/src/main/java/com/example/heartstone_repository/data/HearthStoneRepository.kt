package com.example.heartstone_repository.data

import com.example.heartstone_repository.model.InfoResponse
import retrofit2.Response

interface HearthStoneRepository {
    suspend fun getInfo(): Response<InfoResponse>
}