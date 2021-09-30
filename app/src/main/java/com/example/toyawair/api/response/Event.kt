package com.example.toyawair.api.response

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("end")
    val end: String,
    @SerializedName("start")
    val start: String,
    @SerializedName("title")
    val title: String
)