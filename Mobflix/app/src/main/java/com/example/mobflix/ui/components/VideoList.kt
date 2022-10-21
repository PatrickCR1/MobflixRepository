package com.example.mobflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.compose.ui.theme.*
import com.example.mobflix.R
import com.example.mobflix.model.VideoModel


@Composable
fun VideoCard(videoModel: VideoModel) {
    Box(
        modifier = Modifier
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
fun VideoList(videoList: List<VideoModel>) {
    LazyColumn(
        Modifier
            .testTag(stringResource(id = R.string.video_list))
    ) {
        items(videoList) {
            VideoRow(videoModel = it)
        }
    }
}


@Composable
fun VideoRow(videoModel: VideoModel) {
    Column(
        Modifier
            .testTag(stringResource(id = R.string.video_row))
            .padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (videoModel.category == R.string.front_end_video_section) {
            VideoCategory(
                name = stringResource(id = R.string.front_end_video_section),
                backgroundColor = BlueBackgroundVideoCategory
            )

        } else if (videoModel.category == R.string.programming_video_section) {
            VideoCategory(
                name = stringResource(id = R.string.programming_video_section),
                backgroundColor = GreenBackgroundVideoCategory
            )
        } else {
            VideoCategory(
                name = stringResource(id = R.string.mobile_video_section),
                backgroundColor = RedBackgroundVideoCategory
            )
        }
        VideoCard(videoModel = videoModel)
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
    VideoList(videoList = videoLisSample)
}

@Preview()
@Composable()
private fun VideoRowPreview() {
    VideoRow(videoModelSample)
}