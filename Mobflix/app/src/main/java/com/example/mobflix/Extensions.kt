package com.example.mobflix

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.mobflix.service.model.category.CategoryDatabaseModel
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoDatabaseModel
import com.example.mobflix.service.model.video.VideoModel

fun VideoModel.toVideoDatabaseModel() = VideoDatabaseModel(
    id = id,
    url = url,
    image = image,
    category = category,
)

fun VideoDatabaseModel.toVideoModel(color: Color) = VideoModel(
    id = id,
    url = url,
    image = image,
    category = category,
    categoryColor = color,
)

fun CategoryModel.toCategoryDatabaseModel() = CategoryDatabaseModel(
    id = id,
    category = category,
    color = color.toArgb()
)

fun CategoryDatabaseModel.toCategoryModel() = CategoryModel(
    id = id,
    category = category,
    color = Color(color)
)
