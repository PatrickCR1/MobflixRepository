package com.example.mobflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.ui.theme.smallPadding
import com.example.mobflix.ui.theme.smallSpacedBy
import com.example.mobflix.ui.theme.verySmallPadding
import com.example.mobflix.ui.viewmodel.MainViewModel
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoList(viewModel: MainViewModel = getViewModel()) {
    val videoList = viewModel.videoList.observeAsState().value
    LazyColumn(
        Modifier
            .testTag(stringResource(id = R.string.video_list))
    ) {
        if (!videoList!!.isEmpty()) {
            items(videoList!!) {
                VideoRow(videoModel = it)
                Spacer(modifier = Modifier.height(verySmallPadding))
            }
        } else {
            items(1) {
                VideoRow(DarkGoldBackgroundVideoCategory)
                Spacer(modifier = Modifier.height(verySmallPadding))
                VideoRow(PurpleBackgroundVideoCategory)
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
            backgroundColor = videoModel.categoryColor
        )
    }
    VideoCard(videoModel = videoModel)
}

@Composable
fun VideoRow(backgroundColor: Color) {
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
            backgroundColor = backgroundColor
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