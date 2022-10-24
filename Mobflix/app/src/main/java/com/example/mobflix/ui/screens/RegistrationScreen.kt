package com.example.mobflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.BlackBackground
import com.example.mobflix.R
import com.example.mobflix.ui.components.RegistrationFields
import com.example.mobflix.ui.components.RegistrationText
import com.example.mobflix.ui.components.VideoPreviewSection
import com.example.mobflix.ui.theme.mediumSpacer
import com.example.mobflix.ui.theme.smallSpacer

@Composable
fun RegistrationScreen() {
    Column(Modifier
        .fillMaxSize()
        .background(BlackBackground)) {
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