package com.example.toyawair.data.repo

import com.example.toyawair.api.response.AwairResponse

interface AwairRepository {
    suspend fun getEvent(
        nextPageToken: String
    ): Result<AwairResponse>
}