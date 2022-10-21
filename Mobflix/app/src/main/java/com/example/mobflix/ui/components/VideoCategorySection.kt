package com.example.mobflix.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.*
import com.example.mobflix.R

@Composable
fun VideoCategorySection() {
    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState(0))
            .testTag(stringResource(id = R.string.video_section_row)),
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Spacer(modifier = Modifier)
        VideoCategoryClickable(
            name = stringResource(id = R.string.front_end_video_section),
            backgroundColor = BlueBackgroundVideoCategory
        )
        VideoCategoryClickable(
            name = stringResource(id = R.string.programming_video_section),
            backgroundColor = GreenBackgroundVideoCategory
        )
        VideoCategoryClickable(
            name = stringResource(id = R.string.mobile_video_section),
            backgroundColor = RedBackgroundVideoCategory
        )
        Spacer(modifier = Modifier)
    }
}

@Preview()
@Composable
private fun VideoCategorySectionPreview() {
    VideoCategorySection()
}