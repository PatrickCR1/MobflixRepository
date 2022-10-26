package com.example.mobflix.service.model.api

import com.google.gson.annotations.SerializedName

data class TopResponse(
    @SerializedName("items")
    var items: List<Item>
)