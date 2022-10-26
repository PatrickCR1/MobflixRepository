package com.example.mobflix.service.model.api

import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("high")
    var high: HighResolution,
)