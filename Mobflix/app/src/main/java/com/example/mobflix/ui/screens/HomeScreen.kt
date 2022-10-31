package com.example.mobflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.BlackBackground
import com.example.mobflix.ui.components.*
import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel = getViewModel()) {

    Scaffold(floatingActionButton = { FabIcon(onClick = {viewModel.navigationRegistrationScreen()})}) { contentPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(BlackBackground)
        ) {
            AppName()
            HighlightVideo(videoModel = videoModelSample)
            Spacer(modifier = Modifier.height(16.dp))
            VideoCategorySection()
            Spacer(modifier = Modifier.height(8.dp))
            VideoList()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
