package com.example.toyawair.api

import com.example.toyawair.api.response.AwairResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AwairApi {

    @GET("events")
    fun getEvents(
        @Query("next_page_token") nextPageToken: String = ""
    ) : Call<AwairResponse>

}