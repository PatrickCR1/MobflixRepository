package com.example.mobflix

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobflix.service.repository.local.VideoDatabase
import com.example.mobflix.ui.components.AppName
import com.example.mobflix.ui.view.activity.MainActivity
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep


class VideoRegistrationTests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun settingEnvironment() {
        VideoDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().targetContext).clearAllTables()
    }

    private fun fillRegistrationFields(url: String, category: String) {
        val registrationEditTextString = composeTestRule.activity.getString(R.string.registration_field_edit_text)
        val registrationButtonString = composeTestRule.activity.getString(R.string.registration_button)
        composeTestRule.onAllNodesWithTag(registrationEditTextString)[0].performTextInput(url)
        composeTestRule.onAllNodesWithTag(registrationEditTextString)[1].performTextInput(category)
        composeTestRule.onNodeWithTag(registrationButtonString).performClick()
    }

    @Test
    fun WhenUrlAndCategoryAreValidTheVideoShouldBeSavedAndReturnToHomeScreen() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val appNameString = composeTestRule.activity.getString(R.string.app_name)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        fillRegistrationFields("https://youtu.be/ijgYsmthKWU", "Mobile")
        sleep(500)
        composeTestRule.onNodeWithText(appNameString).assertIsDisplayed()
    }

    @Test
    fun WhenUrlAndCategoryAreValidTheSnackBarShouldNotBeDisplayedAndTheTestShouldFail() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val snackBarString = composeTestRule.activity.getString(R.string.ERROR_INVALID_FIELDS)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        fillRegistrationFields("https://youtu.be/ijgYsmthKWU", "Mobile")
        composeTestRule.onNodeWithText(snackBarString).assertIsDisplayed()

    }

    @Test
    fun WhenUrlIsInvalidShouldShowSnackBar() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val snackBarString = composeTestRule.activity.getString(R.string.ERROR_INVALID_FIELDS)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        fillRegistrationFields("asdasdasdas", "Mobile")
        composeTestRule.onNodeWithText(snackBarString).assertIsDisplayed()
    }

    @Test
    fun WhenCategoryIsEmptyShouldShowSnackBar() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val snackBarString = composeTestRule.activity.getString(R.string.ERROR_INVALID_FIELDS)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        fillRegistrationFields("https://youtu.be/ijgYsmthKWU", "")
        composeTestRule.onNodeWithText(snackBarString).assertIsDisplayed()
    }
}