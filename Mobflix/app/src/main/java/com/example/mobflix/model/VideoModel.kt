package com.example.mobflix.model

import com.example.mobflix.R

data class VideoModel(
    val url: String,
    val image: String,
    val category: Int = R.string.mobile_video_section,
)
