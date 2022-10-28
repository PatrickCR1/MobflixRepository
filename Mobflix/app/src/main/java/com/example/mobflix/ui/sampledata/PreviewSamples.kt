package com.example.mobflix.ui.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoModel

val videoModelSample = VideoModel(
    url = "https://youtu.be/ijgYsmthKWU",
    image = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"
)

val categoryModelSample = CategoryModel(
    category = "Mobile"
)

val videoListSample = mutableListOf<VideoModel>().apply { repeat(2) { this.add(videoModelSample) } }
val categoryListSample = mutableListOf<CategoryModel>().apply { repeat(2) { this.add(categoryModelSample) } }

val imageSample = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"

val stringSample = ""

val sampleMutableLiveData = MutableLiveData<String>()

fun exampleFun() {}


