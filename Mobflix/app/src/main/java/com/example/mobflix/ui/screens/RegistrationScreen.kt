package com.example.mobflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.BlackBackground
import com.example.mobflix.R
import com.example.mobflix.ui.components.RegistrationFields
import com.example.mobflix.ui.components.RegistrationText
import com.example.mobflix.ui.components.VideoPreviewSection
import com.example.mobflix.ui.theme.smallSpacer
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationScreen(viewModel: VideoRegistrationViewModel = getViewModel()) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) { contentPadding ->
        SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
            Snackbar(
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
            RegistrationText(name = stringResource(id = R.string.video_registration))
            Spacer(Modifier.height(smallSpacer))
            RegistrationFields()
            Spacer(Modifier.height(smallSpacer))
            VideoPreviewSection()
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
    RegistrationScreen()
}