package com.example.toyawair.api

import retrofit2.http.GET
import retrofit2.http.Query

interface AwairApi {

    companion object {
        private const val BASE_AWAIR_URL = "https://mobile-app-interview.awair.is/"
    }


    @GET("events")
    fun getEvents(
        @Query("next_page_token") nextPageToken: String
    )

}