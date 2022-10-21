package com.example.mobflix

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.mobflix.ui.view.activity.MainActivity
import org.junit.Rule
import org.junit.Test


class HomeScreenTests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun appNameShouldBeDisplayed() {
        val appNameString = composeTestRule.activity.getString(R.string.app_name)
        val appNameColumnTagString = composeTestRule.activity.getString(R.string.app_name_full_column)
        composeTestRule.onNodeWithText(appNameString).assertIsDisplayed()
        composeTestRule.onNodeWithText(appNameString).assertHeightIsAtLeast(34.dp)
        composeTestRule.onNodeWithText(appNameString).assertWidthIsAtLeast(390.dp)
        composeTestRule.onNodeWithTag(appNameColumnTagString).assertHeightIsAtLeast(86.dp)
        composeTestRule.onNodeWithTag(appNameColumnTagString).assertWidthIsAtLeast(390.dp)
    }

    @Test
    fun highlightVideoShouldBeDisplayedAndButtonBeClickabe() {
        val watchNowString = composeTestRule.activity.getString(R.string.watch_now)
        val highlightVideoBoxString = composeTestRule.activity.getString(R.string.highlight_video_box)
        composeTestRule.onNodeWithTag(highlightVideoBoxString).assertIsDisplayed()
        composeTestRule.onNodeWithTag(highlightVideoBoxString).assertHeightIsAtLeast(138.dp)
        composeTestRule.onNodeWithTag(highlightVideoBoxString).assertWidthIsAtLeast(390.dp)
        composeTestRule.onNodeWithText(watchNowString).assertIsDisplayed()
        composeTestRule.onNodeWithText(watchNowString).assertHeightIsAtLeast(19.dp)
        composeTestRule.onNodeWithText(watchNowString).assertWidthIsAtLeast(69.dp)
        composeTestRule.onNodeWithText(watchNowString).assert(hasClickAction())
    }

    @Test
    fun videoCategorySectionShouldBeDisplayedAndItemsBeClickable() {
        val videoCategoryClickableString = composeTestRule.activity.getString(R.string.video_category_clickable)
        val videoSectionRowString = composeTestRule.activity.getString(R.string.video_section_row)
        composeTestRule.onAllNodesWithTag(videoCategoryClickableString).assertAll(hasClickAction())
        composeTestRule.onAllNodesWithTag(videoCategoryClickableString)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoCategoryClickableString)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoCategoryClickableString)[2].assertIsDisplayed()
        composeTestRule.onNodeWithTag(videoSectionRowString).assert(hasScrollAction())
        composeTestRule.onNodeWithTag(videoSectionRowString).assertHeightIsAtLeast(19.dp)
        composeTestRule.onNodeWithTag(videoSectionRowString).assertWidthIsAtLeast(390.dp)
    }

    @Test
    fun videoListShouldBeDisplayedAndVideoCardBeClickable() {
        val videoListString = composeTestRule.activity.getString(R.string.video_list)
        val videoRowString = composeTestRule.activity.getString(R.string.video_row)
        val videoCardString = composeTestRule.activity.getString(R.string.video_card)
        val videoCategoryString = composeTestRule.activity.getString(R.string.video_category)
        composeTestRule.onNodeWithTag(videoListString).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoRowString)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoRowString)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoCardString)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoCardString)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoCardString)[0].assert(hasClickAction())
        composeTestRule.onAllNodesWithTag(videoCardString)[1].assert(hasClickAction())
        composeTestRule.onAllNodesWithTag(videoCategoryString)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(videoCategoryString)[1].assertIsDisplayed()
    }

    @Test
    fun floatingActionButtonShouldBeDisplayedAndBeClickabe() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        composeTestRule.onNodeWithTag(fabTagString).assertIsDisplayed()
        composeTestRule.onNodeWithTag(fabTagString).assert(hasClickAction())
    }
}