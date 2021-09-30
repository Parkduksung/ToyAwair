package com.example.toyawair.data.repo

import com.example.toyawair.api.response.AwairResponse
import com.example.toyawair.utils.Result

interface AwairRepository {
    suspend fun getEvent(
        nextPageToken: String
    ): Result<AwairResponse>
}