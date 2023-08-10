package com.example.heartstone_repository.data

import com.example.heartstone_repository.model.InfoResponse
import com.example.heartstone_repository.repository.HearthStoneAPI
import com.example.heartstone_repository.utils.extensions.handleErrors
import retrofit2.Response

class HearthStoneDataRepository(
    private val api: HearthStoneAPI
) : HearthStoneRepository {

    override suspend fun getInfo(): InfoResponse {
        return api.getInfo().handleErrors()
    }
}