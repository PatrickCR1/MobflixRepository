package com.example.mobflix

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.mobflix.ui.view.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class VideoEditScreenTests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    val validUrl = "https://youtu.be/ijgYsmthKWU"
    val validCategory = "Mobile"

    @Before
    fun settingEnvironment() {
        createNewVideo(validUrl, validCategory)
        sleep(500)
    }

    private fun createNewVideo(url: String, category: String) {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val registrationEditTextString = composeTestRule.activity.getString(R.string.registration_field_edit_text)
        val registrationButtonString = composeTestRule.activity.getString(R.string.registration_button)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        composeTestRule.onAllNodesWithTag(registrationEditTextString)[0].performTextInput(url)
        composeTestRule.onAllNodesWithTag(registrationEditTextString)[1].performTextInput(category)
        composeTestRule.onNodeWithTag(registrationButtonString).performClick()
    }

    @Test
    fun registrationTextShouldBeDisplayed() {
        val videoCardString = composeTestRule.activity.getString(R.string.video_card)
        composeTestRule.onAllNodesWithTag(videoCardString)[0].performSemanticsAction(SemanticsActions.OnLongClick)
        val videoEditString = composeTestRule.activity.getString(R.string.video_edit)
        composeTestRule.onNodeWithText(videoEditString).assertIsDisplayed()
    }

    @Test
    fun registrationFieldsShouldBeDisplayed() {
        val videoCardString = composeTestRule.activity.getString(R.string.video_card)
        composeTestRule.onAllNodesWithTag(videoCardString)[0].performSemanticsAction(SemanticsActions.OnLongClick)
        val registrationFieldTextString = composeTestRule.activity.getString(R.string.registration_field_text)
        val registrationFieldEditTextString = composeTestRule.activity.getString(R.string.registration_field_edit_text)
        composeTestRule.onAllNodesWithTag(registrationFieldTextString)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(registrationFieldTextString)[0].assertHeightIsAtLeast(16.dp)
        composeTestRule.onAllNodesWithTag(registrationFieldTextString)[0].assertWidthIsAtLeast(30.dp)
        composeTestRule.onAllNodesWithTag(registrationFieldTextString)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(registrationFieldTextString)[1].assertHeightIsAtLeast(16.dp)
        composeTestRule.onAllNodesWithTag(registrationFieldTextString)[1].assertWidthIsAtLeast(30.dp)
        composeTestRule.onAllNodesWithTag(registrationFieldEditTextString)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(registrationFieldEditTextString)[0].assertHeightIsAtLeast(48.dp)
        composeTestRule.onAllNodesWithTag(registrationFieldEditTextString)[0].assertWidthIsAtLeast(320.dp)
        composeTestRule.onAllNodesWithTag(registrationFieldEditTextString)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(registrationFieldEditTextString)[1].assertHeightIsAtLeast(48.dp)
        composeTestRule.onAllNodesWithTag(registrationFieldEditTextString)[1].assertWidthIsAtLeast(320.dp)
    }

    @Test
    fun videoPreviewSectionShouldBeDisplayed() {
        val videoCardString = composeTestRule.activity.getString(R.string.video_card)
        composeTestRule.onAllNodesWithTag(videoCardString)[0].performSemanticsAction(SemanticsActions.OnLongClick)
        val videoPreviewString = composeTestRule.activity.getString(R.string.preview)
        val videoCardPreviewString = composeTestRule.activity.getString(R.string.video_card_preview)
        val registrationButtonString = composeTestRule.activity.getString(R.string.registration_button)
        composeTestRule.onNodeWithText(videoPreviewString).assertIsDisplayed()
        composeTestRule.onNodeWithTag(videoCardPreviewString).assertIsDisplayed()
        composeTestRule.onNodeWithTag(videoCardPreviewString).assertHeightIsAtLeast(220.dp)
        composeTestRule.onNodeWithTag(videoCardPreviewString).assertWidthIsAtLeast(320.dp)
        composeTestRule.onAllNodesWithTag(registrationButtonString)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(registrationButtonString)[0].assertHeightIsAtLeast(48.dp)
        composeTestRule.onAllNodesWithTag(registrationButtonString)[0].assertWidthIsAtLeast(320.dp)
        composeTestRule.onAllNodesWithTag(registrationButtonString)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(registrationButtonString)[1].assertHeightIsAtLeast(48.dp)
        composeTestRule.onAllNodesWithTag(registrationButtonString)[1].assertWidthIsAtLeast(320.dp)
        composeTestRule.onAllNodesWithTag(registrationButtonString).assertCountEquals(2)
    }
}
