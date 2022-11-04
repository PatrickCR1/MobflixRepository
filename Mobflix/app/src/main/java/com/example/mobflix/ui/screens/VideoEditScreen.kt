package com.example.mobflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.BlackBackground
import com.example.mobflix.R
import com.example.mobflix.ui.components.RegistrationFields
import com.example.mobflix.ui.components.ScreenTitleText
import com.example.mobflix.ui.components.VideoPreviewSectionEditVideoScreen
import com.example.mobflix.ui.theme.smallSpacer
import com.example.mobflix.ui.viewmodel.VideoEditViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoEditScreen(viewModel: VideoEditViewModel = getViewModel()) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val urlText = viewModel.urlText.observeAsState().value
    val categoryText = viewModel.categoryText.observeAsState().value
    val image = viewModel.image.value

    Scaffold(scaffoldState = scaffoldState) { contentPadding ->
        SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
            Snackbar(
                Modifier.testTag(stringResource(id = R.string.snack_bar))
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data.message, textAlign = TextAlign.Center
                )
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(BlackBackground)
        ) {
            Spacer(Modifier.height(smallSpacer))
            ScreenTitleText(name = stringResource(id = R.string.video_edit))
            Spacer(Modifier.height(smallSpacer))
            urlText?.let {
                categoryText?.let { it1 ->
                    RegistrationFields(
                        urlText = it,
                        categoryText = it1,
                        onUrlChangedFunction = { viewModel.onUrlTextChanged(it) },
                        onCategoryChangedFunction = { viewModel.onCategoryChanged(it) })
                }
            }
            Spacer(Modifier.height(smallSpacer))
            categoryText?.let {
                VideoPreviewSectionEditVideoScreen(
                    categoryText = it,
                    image = image,
                    updateButtonFunction = { viewModel.videoUpdate() },
                    removeButtonFunction = { viewModel.videoRemove() })
            }
        }
        LaunchedEffect(key1 = Unit) {
            viewModel.snackBar.observeForever {
                coroutineScope.launch {
                    if (it != "") {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = it
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegistrationScreenPreview() {
    VideoEditScreen()
}
