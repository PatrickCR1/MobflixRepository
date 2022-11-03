package com.example.mobflix.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
fun VideoList(videoList: List<VideoModel>, onClick: (String) -> Unit, onLongClick: (VideoModel) -> Unit) {
    LazyColumn(
        Modifier
            .testTag(stringResource(id = R.string.video_list))
    ) {
        if (!videoList!!.isEmpty()) {
            items(videoList!!) {
                VideoRow(videoModel = it, onClick = {onClick(it.url)}, onLongClick = {onLongClick(it)})
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
fun VideoRow(videoModel: VideoModel, onClick: () -> Unit, onLongClick: () -> Unit) {
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
        Row {
            VideoCategory(
                name = videoModel.category,
                backgroundColor = videoModel.categoryColor)
            Spacer(modifier = Modifier.width(smallPadding))
            FavoriteButton(video = videoModel)
        }
    }
    VideoCard(videoModel = videoModel, onClick = onClick, onLongClick = onLongClick)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoCard(videoModel: VideoModel, onClick: () -> Unit, onLongClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = smallPadding, end = smallPadding, top = verySmallPadding)
            .testTag(stringResource(id = R.string.video_card))
            .combinedClickable(onClick = onClick, onLongClick = onLongClick), contentAlignment = Alignment.Center
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
    VideoCard(videoModel = videoModelSample, onClick = {}, onLongClick = {})
}

@Preview()
@Composable
private fun VideoListPreview() {
    VideoList(videoListSample, onClick = {exampleFun()}, onLongClick = {exampleFun()})
}

@Preview()
@Composable()
private fun VideoRowPreview() {
    VideoRow(videoModel = videoModelSample, onClick = {exampleFun()}, onLongClick = {exampleFun()})
}
