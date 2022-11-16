package com.example.mobflix.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.PurpleBackgroundVideoCategory
import com.example.compose.ui.theme.DarkGoldBackgroundVideoCategory
import com.example.mobflix.R
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.ui.theme.mediumSpacedBy
import com.example.mobflix.ui.theme.smallPadding
import com.example.mobflix.ui.theme.verySmallPadding
import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoCategorySection(categoryList: List<CategoryModel>, function: (String) -> Unit) {
    LazyRow(
        Modifier
            .testTag(stringResource(id = R.string.video_section_row))
            .padding(start = smallPadding),
        horizontalArrangement = Arrangement.spacedBy(mediumSpacedBy)
    ) {
        if (!categoryList!!.isEmpty()) {
            items(categoryList!!) {
                VideoCategoryClickable(name = it.category, backgroundColor = it.color, function = {function(it.category)})
            }
        } else {
            items(1) {
                VideoCategoryClickable(name = stringResource(id = R.string.your_category), backgroundColor = DarkGoldBackgroundVideoCategory, function = {})
                Spacer(modifier = Modifier.width(verySmallPadding))
                VideoCategoryClickable(name = stringResource(id = R.string.your_category), backgroundColor = PurpleBackgroundVideoCategory, function = {})
            }
        }
    }
}

@Preview()
@Composable
private fun VideoCategorySectionPreview() {
    VideoCategorySection(categoryListSample) {
        exampleFun()
    }
}