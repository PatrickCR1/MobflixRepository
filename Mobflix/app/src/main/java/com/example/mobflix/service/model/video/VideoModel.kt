package com.example.mobflix.service.model.video

import androidx.compose.ui.graphics.Color

data class VideoModel (
    val id: Int = 0,
    val url: String = "",
    val image: String = "",
    val category: String = "",
    val categoryColor: Color = Color.Cyan,
        )
