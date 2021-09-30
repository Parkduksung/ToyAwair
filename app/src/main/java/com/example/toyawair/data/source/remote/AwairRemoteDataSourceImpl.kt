package com.example.toyawair.data.source.remote

import com.example.toyawair.api.AwairApi
import com.example.toyawair.api.response.AwairResponse
import com.example.toyawair.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AwairRemoteDataSourceImpl @Inject constructor(private val awairApi: AwairApi) :
    AwairRemoteDataSource {

    override suspend fun getEvent(nextPageToken: String): Result<AwairResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(awairApi.getEvents(nextPageToken = nextPageToken).execute().body()!!)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}