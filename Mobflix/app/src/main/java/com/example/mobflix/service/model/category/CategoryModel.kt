package com.example.mobflix.service.model.category

import androidx.compose.ui.graphics.Color
import com.example.mobflix.ui.components.listOfColors

data class CategoryModel (
    var id: Int = 0,
    val category: String = "",
    val color: Color = listOfColors.random()
        )
