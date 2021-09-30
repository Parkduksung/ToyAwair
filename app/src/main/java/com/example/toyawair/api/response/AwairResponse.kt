package com.example.toyawair.api.response

import com.google.gson.annotations.SerializedName

data class AwairResponse(
    @SerializedName("events")
    val events: List<Event>,
    @SerializedName("next_page_token")
    val next_page_token: String
)