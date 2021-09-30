package com.example.toyawair.data.repo

import com.example.toyawair.api.response.AwairResponse
import com.example.toyawair.data.source.remote.AwairRemoteDataSource
import com.example.toyawair.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AwairRepositoryImpl @Inject constructor(private val awairRemoteDataSource: AwairRemoteDataSource) :
    AwairRepository {

    override suspend fun getEvent(nextPageToken: String): Result<AwairResponse> =
        withContext(Dispatchers.IO) {
            return@withContext awairRemoteDataSource.getEvent(nextPageToken = nextPageToken)
        }
}