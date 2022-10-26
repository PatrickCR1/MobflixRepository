package com.example.mobflix.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.White
import com.example.mobflix.R
import com.example.mobflix.ui.theme.mediumPadding
import com.example.mobflix.ui.theme.robotoFamily

@Composable
fun RegistrationText(name: String) {
    Column() {
        Text(
            text = name,
            modifier = Modifier
                .height(38.dp)
                .fillMaxWidth(),
            color = White,
            fontFamily = robotoFamily,
            fontWeight = FontWeight(700),
            fontSize = 32.sp,
            lineHeight = 38.sp,
        )
    }
}

@Preview
@Composable
private fun RegistrationTextPreview() {
    RegistrationText(stringResource(id = R.string.video_registration))
}