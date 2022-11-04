package com.example.mobflix.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.example.compose.ui.theme.FavoriteRed
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteButton(
    video: VideoModel,
    modifier: Modifier = Modifier,
    color: Color = FavoriteRed,
    function: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    isFavorite = video.favorite

    IconToggleButton(
        checked = video.favorite,
        onCheckedChange = {
            function.invoke()
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.7f
                scaleY = 1.7f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}
