package com.example.mobflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.compose.ui.theme.*
import com.example.mobflix.R
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.ui.theme.smallPadding
import com.example.mobflix.ui.theme.smallSpacedBy
import com.example.mobflix.ui.theme.verySmallPadding
import com.example.mobflix.ui.viewmodel.MainViewModel
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoList(viewModel: MainViewModel = getViewModel()) {
    LazyColumn(
        Modifier
            .testTag(stringResource(id = R.string.video_list))
    ) {
        if (!viewModel.videoList.value!!.isEmpty()) {
            items(viewModel.videoList.value!!) {
                VideoRow(videoModel = it)
                Spacer(modifier = Modifier.height(verySmallPadding))
            }
        } else {
            items(2) {
                VideoRow()
                Spacer(modifier = Modifier.height(verySmallPadding))
            }
        }
    }
}


@Composable
fun VideoRow(videoModel: VideoModel, viewModel: VideoRegistrationViewModel = getViewModel()) {
    Column(
        Modifier
            .testTag(stringResource(id = R.string.video_row))
            .padding(
                start = smallPadding,
                end = smallPadding,
                top = verySmallPadding,
                bottom = verySmallPadding
            ),
        verticalArrangement = Arrangement.spacedBy(smallSpacedBy)
    ) {
        VideoCategory(
            name = videoModel.category,
            backgroundColor = viewModel.getCategoryColor(videoModel)
        )
    }
    VideoCard(videoModel = videoModel)
}

@Composable
fun VideoRow() {
    Column(
        Modifier
            .testTag(stringResource(id = R.string.video_row))
            .padding(
                start = smallPadding,
                end = smallPadding,
                top = verySmallPadding,
                bottom = verySmallPadding
            ),
        verticalArrangement = Arrangement.spacedBy(smallSpacedBy)
    ) {
        VideoCategory(
            name = stringResource(id = R.string.your_category),
            backgroundColor = RedBackgroundVideoCategory
        )
    }
    VideoCard()
}

@Composable
fun VideoCard(videoModel: VideoModel) {
    Box(
        modifier = Modifier
            .padding(start = smallPadding, end = smallPadding, top = verySmallPadding)
            .testTag(stringResource(id = R.string.video_card))
            .clickable { }, contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = videoModel.image), modifier = Modifier
                .fillMaxSize()
                .height(220.dp),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun VideoCard() {
    Box(
        modifier = Modifier
            .padding(start = smallPadding, end = smallPadding, top = verySmallPadding)
            .testTag(stringResource(id = R.string.video_card))
            .clickable { }, contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.youtubeimage), modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview()
@Composable
private fun VideoCardPreview() {
    VideoCard(videoModel = videoModelSample)
}

@Preview()
@Composable
private fun VideoListPreview() {
    VideoList()
}

@Preview()
@Composable()
private fun VideoRowPreview() {
    VideoRow(videoModelSample)
}