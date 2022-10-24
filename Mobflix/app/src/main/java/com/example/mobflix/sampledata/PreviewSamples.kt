package com.example.mobflix.ui.components

import com.example.mobflix.model.VideoModel
import com.example.mobflix.service.listener.FABListener

val videoModelSample = VideoModel(
    url = "https://youtu.be/ijgYsmthKWU",
    image = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"
)

val videoLisSample = mutableListOf<VideoModel>().apply { repeat(2) { this.add(videoModelSample) } }

val image = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"

val sampleFabListener = object : FABListener {
    override fun onFABClick() {
    }}
