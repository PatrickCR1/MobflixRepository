package com.example.mobflix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.LightBlue
import com.example.compose.ui.theme.White
import com.example.mobflix.R
import com.example.mobflix.ui.theme.*

@Composable
fun VideoRegistrationButton() {
    Surface(
        shape = RoundedCornerShape(smallCornerShape),
        modifier = Modifier
            .padding(
                start = mediumPadding,
                end = mediumPadding,
                top = smallPadding
            )
    ) {
        Box(
            Modifier
                .testTag(stringResource(id = R.string.registration_button))
                .background(LightBlue)
                .height(48.dp)
                .fillMaxWidth()
                .clickable {  },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.registration),
                fontFamily = robotoFamily,
                fontWeight = FontWeight(normalLightFontWeight),
                fontSize = 18.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                color = White,
            )
        }
    }
}

@Preview
@Composable
private fun VideoRegistrationButtonPreview() {
    VideoRegistrationButton()
}