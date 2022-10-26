package com.example.mobflix.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.BlueBackgroundVideoCategory
import com.example.compose.ui.theme.GreenBackgroundVideoCategory
import com.example.compose.ui.theme.RedBackgroundVideoCategory
import com.example.mobflix.R
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.ui.theme.mediumSpacedBy
import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoCategorySection(viewModel: MainViewModel = getViewModel()) {
    LazyRow(
        Modifier
            .testTag(stringResource(id = R.string.video_section_row)),
        horizontalArrangement = Arrangement.spacedBy(mediumSpacedBy)
    ) {
        items(viewModel.categoryList.value!!) {
            VideoCategoryClickable(name = it.category, backgroundColor = it.color)
        }
    }
}

@Preview()
@Composable
private fun VideoCategorySectionPreview() {
    VideoCategorySection()
}