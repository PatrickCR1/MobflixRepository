package com.example.mobflix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.BlueBackgroundVideoCategory
import com.example.compose.ui.theme.White
import com.example.mobflix.R
import com.example.mobflix.ui.theme.mediumCornerShape
import com.example.mobflix.ui.theme.normalLightFontWeight
import com.example.mobflix.ui.theme.robotoFamily

@Composable
fun VideoCategory(name: String, backgroundColor: Color) {
    Surface(
        shape = RoundedCornerShape(mediumCornerShape)
    ) {
        Text(
            text = name,
            Modifier
                .testTag(stringResource(id = R.string.video_category))
                .background(backgroundColor)
                .padding(top = 7.dp, bottom = 6.dp, start = 23.dp, end = 22.dp)
                .sizeIn(minWidth = 69.dp, minHeight = 19.dp),
            fontFamily = robotoFamily,
            fontWeight = FontWeight(normalLightFontWeight),
            fontSize = 16.sp,
            lineHeight = 19.sp,
            textAlign = TextAlign.Center,
            color = White,
        )
    }
}

@Composable
fun VideoCategoryClickable(name: String, backgroundColor: Color) {
    Surface(
        Modifier
            .clickable { }
            .testTag(stringResource(id = R.string.video_category_clickable)),
        shape = RoundedCornerShape(mediumCornerShape)
    ) {
        Text(
            text = name,
            Modifier
                .background(backgroundColor)
                .padding(top = 7.dp, bottom = 6.dp, start = 23.dp, end = 22.dp)
                .sizeIn(minWidth = 69.dp, minHeight = 19.dp)
                .clickable { },
            fontFamily = robotoFamily,
            fontWeight = FontWeight(normalLightFontWeight),
            fontSize = 16.sp,
            lineHeight = 19.sp,
            textAlign = TextAlign.Center,
            color = White,
        )
    }
}

@Preview()
@Composable
private fun VideoCategoryPreview() {
    VideoCategory(
        name = stringResource(id = R.string.front_end_video_section),
        backgroundColor = BlueBackgroundVideoCategory
    )
}