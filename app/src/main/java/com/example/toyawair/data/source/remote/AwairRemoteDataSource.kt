package com.example.toyawair.data.source.remote

import com.example.toyawair.api.response.AwairResponse
import com.example.toyawair.utils.Result

interface AwairRemoteDataSource {

    suspend fun getEvent(
        nextPageToken: String
    ): Result<AwairResponse>
}