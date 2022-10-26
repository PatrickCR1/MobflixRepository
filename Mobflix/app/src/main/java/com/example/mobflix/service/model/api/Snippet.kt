package com.example.mobflix.service.model.api

import com.google.gson.annotations.SerializedName

data class Snippet (
    @SerializedName("thumbnails")
    var thumbnails: Thumbnails,
        )