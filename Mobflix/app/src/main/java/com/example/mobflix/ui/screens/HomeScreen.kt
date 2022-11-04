package com.example.mobflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.BlackBackground
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.ui.components.*
import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel = getViewModel()) {
    val videoList = viewModel.videoList.observeAsState().value
    val categoryList = viewModel.categoryList.observeAsState().value
    Scaffold(floatingActionButton = { FabIcon(
        onClick = { viewModel.navigationRegistrationScreen() })})
    { contentPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(BlackBackground)
        ) {
            AppName()
            videoList?.let {
                PickHighlightVideo(videoList, viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                categoryList?.let { it1 ->
                    VideoCategorySection(it1) {
                        viewModel.getFilteredVideoList(it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                VideoList(
                    videoList,
                    onClick = { viewModel.navigationYoutube(it) },
                    onLongClick = { viewModel.navigationEditScreen(it) },
                    favoriteButtonFunction = { viewModel.isVideoFavorite(it) })
            }
        }
    }
}

@Composable
private fun PickHighlightVideo(
    videoList: List<VideoModel>,
    viewModel: MainViewModel
) {
    if (!videoList.none { it.favorite }) {
        val videoModel = viewModel.getFavoriteVideoAtRandom()
        HighlightVideo(videoModel = videoModel) {
            viewModel.navigationYoutube(videoModel.url)
        }
    } else if (videoList.isNotEmpty()) {
        val videoModel = viewModel.getVideoAtRandom()
        HighlightVideo(videoModel = videoModel) {
            viewModel.navigationYoutube(videoModel.url)
        }
    } else {
        HighlightVideo(videoModel = videoModelSample) {
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
