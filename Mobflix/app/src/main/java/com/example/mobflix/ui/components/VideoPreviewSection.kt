package com.example.mobflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.compose.ui.theme.BlueEditText
import com.example.mobflix.R
import com.example.mobflix.ui.theme.mediumPadding
import com.example.mobflix.ui.theme.smallPadding
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoPreviewSection(viewModel: VideoRegistrationViewModel = getViewModel()) {
    val categoryText = viewModel.categoryText.observeAsState().value
    Column(Modifier.padding(start = mediumPadding, end = mediumPadding)) {
        RegistrationText(name = stringResource(id = R.string.preview))
        Spacer(modifier = Modifier.height(smallPadding))
        if (categoryText != "") {
            VideoCategory(name = viewModel.categoryText.value!!, backgroundColor = BlueEditText)
        }
        if (viewModel.image.value != "") {
        PreviewVideoCard(image = viewModel.image.value)
        } else {
            PreviewVideoCard(image = R.drawable.youtubeimage)
        }
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
            .padding(top = smallPadding, bottom = smallPadding),
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

@Composable
fun PreviewVideoCard(image: Int) {
    Box(
        modifier = Modifier
            .testTag(stringResource(id = R.string.video_card_preview))
            .padding(top = smallPadding),
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
    PreviewVideoCard(image = imageSample)
}
