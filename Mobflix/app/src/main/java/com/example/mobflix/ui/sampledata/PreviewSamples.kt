package com.example.mobflix.ui.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobflix.service.model.video.VideoModel

val videoModelSample = VideoModel(
    url = "https://youtu.be/ijgYsmthKWU",
    image = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"
)

val videoListSample = mutableListOf<VideoModel>().apply { repeat(2) { this.add(videoModelSample) } }

val image = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"

val sampleMutableLiveData = MutableLiveData<String>()

fun exampleFun() {}


