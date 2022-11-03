package com.example.mobflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.compose.ui.theme.LightBlue
import com.example.compose.ui.theme.RedButton
import com.example.mobflix.R
import com.example.mobflix.ui.theme.mediumPadding
import com.example.mobflix.ui.theme.smallPadding
import com.example.mobflix.ui.viewmodel.VideoEditViewModel
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoPreviewSectionRegistrationScreen(
    categoryText: String,
    image: String,
    function: () -> Unit
) {

    Column(Modifier.padding(start = mediumPadding, end = mediumPadding)) {
        PreviewText(name = stringResource(id = R.string.preview))
        Spacer(modifier = Modifier.height(smallPadding))
        if (categoryText != "") {
            VideoCategory(name = categoryText, backgroundColor = BlueEditText)
        }
        if (image != "") {
            PreviewVideoCard(image = image)
        } else {
            PreviewVideoCard(image = R.drawable.youtubeimage)
        }
        VideoButton(
            text = stringResource(id = R.string.registration),
            color = LightBlue,
            function = function
        )
    }
}

@Composable
fun VideoPreviewSectionEditVideoScreen(
    categoryText: String,
    image: String,
    updateButtonFunction: () -> Unit,
    removeButtonFunction: () -> Unit
) {
    Column(
        Modifier
            .padding(start = mediumPadding, end = mediumPadding)
            .verticalScroll(rememberScrollState(0))
    ) {
        PreviewText(name = stringResource(id = R.string.preview))
        Spacer(modifier = Modifier.height(smallPadding))
        if (categoryText != "") {
            VideoCategory(name = categoryText, backgroundColor = BlueEditText)
        }
        if (image != "") {
            PreviewVideoCard(image = image)
        } else {
            PreviewVideoCard(image = R.drawable.youtubeimage)
        }
        VideoButton(
            text = stringResource(id = R.string.edit),
            color = LightBlue,
            function = updateButtonFunction
        )
        VideoButton(
            text = stringResource(id = R.string.remove),
            color = RedButton,
            function = removeButtonFunction
        )
        Spacer(modifier = Modifier.height(mediumPadding))
    }
}

@Preview
@Composable
private fun VideoPreviewSectionRegistrationScreenPreview() {
    VideoPreviewSectionRegistrationScreen(
        categoryText = stringSample,
        image = imageSample,
        function = { exampleFun() })
}

@Preview
@Composable
private fun VideoPreviewSectionEditScreenPreview() {
    VideoPreviewSectionEditVideoScreen(
        categoryText = stringSample,
        image = imageSample,
        updateButtonFunction = { exampleFun() },
        removeButtonFunction = { exampleFun() })
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
