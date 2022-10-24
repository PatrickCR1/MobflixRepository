package com.example.mobflix

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.mobflix.ui.view.activity.MainActivity
import org.junit.Rule
import org.junit.Test

class RegistrationScreenTests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun registrationTextShouldBeDisplayed() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        val videoRegistrationString = composeTestRule.activity.getString(R.string.video_registration)
        composeTestRule.onNodeWithText(videoRegistrationString).assertIsDisplayed()
    }

    @Test
    fun registrationFieldsShouldBeDisplayed() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
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
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        val videoPreviewString = composeTestRule.activity.getString(R.string.preview)
        val videoCardPreviewString = composeTestRule.activity.getString(R.string.video_card_preview)
        val registrationButtonString = composeTestRule.activity.getString(R.string.registration_button)
        composeTestRule.onNodeWithText(videoPreviewString).assertIsDisplayed()
        composeTestRule.onNodeWithTag(videoCardPreviewString).assertIsDisplayed()
        composeTestRule.onNodeWithTag(videoCardPreviewString).assertHeightIsAtLeast(220.dp)
        composeTestRule.onNodeWithTag(videoCardPreviewString).assertWidthIsAtLeast(320.dp)
        composeTestRule.onNodeWithTag(registrationButtonString).assertIsDisplayed()
        composeTestRule.onNodeWithTag(registrationButtonString).assertHeightIsAtLeast(48.dp)
        composeTestRule.onNodeWithTag(registrationButtonString).assertWidthIsAtLeast(320.dp)
    }
}
