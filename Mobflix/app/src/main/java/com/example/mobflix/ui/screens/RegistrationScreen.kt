package com.example.mobflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.BlackBackground
import com.example.mobflix.R
import com.example.mobflix.ui.components.RegistrationFields
import com.example.mobflix.ui.components.RegistrationText
import com.example.mobflix.ui.components.VideoPreviewSection
import com.example.mobflix.ui.theme.smallSpacer
import com.example.mobflix.ui.viewmodel.MainViewModel
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationScreen(viewModel: VideoRegistrationViewModel = getViewModel()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(BlackBackground)
        ) {
            Spacer(Modifier.height(smallSpacer))
            RegistrationText(name = stringResource(id = R.string.video_registration))
            Spacer(Modifier.height(smallSpacer))
            RegistrationFields()
            Spacer(Modifier.height(smallSpacer))
            VideoPreviewSection()
        }
    }

@Preview(showSystemUi = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}