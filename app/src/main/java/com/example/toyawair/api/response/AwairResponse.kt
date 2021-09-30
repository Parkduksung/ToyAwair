package com.example.toyawair.api.response

import com.google.gson.annotations.SerializedName

data class AwairResponse(
    @SerializedName("events")
    val events: List<AwairEvent>,
    @SerializedName("next_page_token")
    val next_page_token: String
)

data class AwairEvent(
    @SerializedName("end")
    val end: String,
    @SerializedName("start")
    val start: String,
    @SerializedName("title")
    val title: String
)