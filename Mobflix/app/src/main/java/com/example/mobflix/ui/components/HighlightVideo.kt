package com.example.mobflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.compose.ui.theme.BlueBackgroundVideoCategory
import com.example.compose.ui.theme.White
import com.example.mobflix.R
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.ui.theme.mediumCornerShape
import com.example.mobflix.ui.theme.normalLightFontWeight
import com.example.mobflix.ui.theme.robotoFamily

@Composable
fun HighlightVideo(videoModel: VideoModel) {
    Box(
        modifier = Modifier
            .testTag(stringResource(id = R.string.highlight_video_box))
            .height(138.dp)
            .fillMaxWidth(),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = videoModel.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Surface(
            Modifier
                .offset(y = 26.dp)
                .align(Alignment.Center)
                .clickable { },
            shape = RoundedCornerShape(mediumCornerShape)

        ) {
            Text(
                text = stringResource(id = R.string.watch_now),
                Modifier
                    .background(BlueBackgroundVideoCategory)
                    .padding(top = 10.dp, bottom = 11.dp, start = 9.dp, end = 9.dp)
                    .sizeIn(minWidth = 69.dp, minHeight = 19.dp)
                    .clickable { },
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
private fun HighlightVideoPreview() {
    HighlightVideo(videoModelSample)
}