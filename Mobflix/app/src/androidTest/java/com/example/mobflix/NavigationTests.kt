package com.example.mobflix

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobflix.service.repository.local.VideoDatabase
import com.example.mobflix.ui.view.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun settingEnvironment() {
        VideoDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().targetContext)
            .clearAllTables()
        createNewVideo(validUrl, validCategory)
        Thread.sleep(500)
    }

    val validUrl = "https://youtu.be/ijgYsmthKWU"
    val validCategory = "Mobile"


    private fun createNewVideo(url: String, category: String) {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val registrationEditTextString =
            composeTestRule.activity.getString(R.string.registration_field_edit_text)
        val registrationButtonString =
            composeTestRule.activity.getString(R.string.registration_button)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        composeTestRule.onAllNodesWithTag(registrationEditTextString)[0].performTextInput(url)
        composeTestRule.onAllNodesWithTag(registrationEditTextString)[1].performTextInput(category)
        composeTestRule.onNodeWithTag(registrationButtonString).performClick()
    }

    @Test
    fun whenClickFABButtonShouldNavigateToVideoRegistrationScreen() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val videoRegistrationString = composeTestRule.activity.getString(R.string.video_registration)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        composeTestRule.onNodeWithText(videoRegistrationString).assertIsDisplayed()
    }

    @Test
    fun WhenLongClickVideoCardShouldNavigateToVideoEditScreen() {
        val videoCardString = composeTestRule.activity.getString(R.string.video_card)
        val videoEditString = composeTestRule.activity.getString(R.string.video_edit)
        composeTestRule.onAllNodesWithTag(videoCardString)[0].performSemanticsAction(
            SemanticsActions.OnLongClick)
        composeTestRule.onNodeWithText(videoEditString).assertIsDisplayed()
    }

    @Test
    fun WhenClickVideoCardShouldNavigateToYoutube() {
        val videoCardString = composeTestRule.activity.getString(R.string.video_card)
        composeTestRule.onAllNodesWithTag(videoCardString)[0].performSemanticsAction(
            SemanticsActions.OnClick)
    }
}
