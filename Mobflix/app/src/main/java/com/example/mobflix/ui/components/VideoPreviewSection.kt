package com.example.mobflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mobflix.R
import com.example.mobflix.ui.theme.mediumPadding
import com.example.mobflix.ui.theme.smallPadding

@Composable
fun VideoPreviewSection() {
    Column() {
        RegistrationText(name = stringResource(id = R.string.preview))
        PreviewVideoCard(image = image)
        VideoRegistrationButton()
    }
}

@Preview
@Composable
private fun VideoPreviewSectionPreview() {
    VideoPreviewSection()
}

@Composable
fun PreviewVideoCard(image: String) {
    Box(
        modifier = Modifier
            .testTag(stringResource(id = R.string.video_card_preview))
            .padding(start = mediumPadding, end = mediumPadding, top = smallPadding),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image), modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
private fun PreviewVideoCardPreview() {
    PreviewVideoCard(image = image)
}
