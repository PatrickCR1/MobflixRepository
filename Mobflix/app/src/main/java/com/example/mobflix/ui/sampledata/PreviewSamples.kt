package com.example.mobflix.ui.components

import androidx.lifecycle.MutableLiveData
import com.example.mobflix.service.model.category.CategoryDatabaseModel
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoDatabaseModel
import com.example.mobflix.service.model.video.VideoModel

val videoModelSample = VideoModel(
    url = "https://youtu.be/ijgYsmthKWU",
    image = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"
)

val videoDatabaseModelSample = VideoDatabaseModel(
    url = "https://youtu.be/ijgYsmthKWU",
    image = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"
)

val categoryModelSample = CategoryModel(
    category = "Mobile"
)

val categoryModelSample2 = CategoryModel(
    category = "Front End"
)

val categoryDatabaseModelSample = CategoryDatabaseModel(
    category = "Mobile"
)

val videoListSample = mutableListOf<VideoModel>().apply { repeat(2) { this.add(videoModelSample) } }
val videoDatabaseListSample = mutableListOf<VideoDatabaseModel>().apply { repeat(2) { this.add(
    videoDatabaseModelSample) } }
val categoryListSample = mutableListOf<CategoryModel>().apply { repeat(2) { this.add(categoryModelSample) } }
val categoryDatabaseListSample = mutableListOf<CategoryDatabaseModel>().apply { repeat(2) { this.add(
    categoryDatabaseModelSample) } }

val imageSample = "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg"

val thumbnailSample = "http://i3.ytimg.com/vi/ijgYsmthKWU/hqdefault.jpg"

val stringSample = ""

val sampleMutableLiveData = MutableLiveData<String>()

fun exampleFun() {}


