package com.example.mobflix

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobflix.service.repository.local.VideoDatabase
import com.example.mobflix.ui.view.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep


class VideoRegistrationTests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun settingEnvironment() {
        clearDatabase()
    }

    val validUrl = "https://youtu.be/ijgYsmthKWU"
    val invalidUrl = "sdsaudasda"
    val validCategory1 = "Mobile"
    val validCategory2 = "Front End"
    val emptyString = ""



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
    fun whenWritingCategoryShouldShowCategoryNameInPreview() {
        val fabTagString = composeTestRule.activity.getString(R.string.floating_action_button_tag)
        val registrationEditTextString = composeTestRule.activity.getString(R.string.registration_field_edit_text)
        val videoCategoryString = composeTestRule.activity.getString(R.string.video_category)
        composeTestRule.onNodeWithTag(fabTagString).performClick()
        composeTestRule.onAllNodesWithTag(registrationEditTextString)[1].performTextInput(validCategory1)
        composeTestRule.onNodeWithTag(videoCategoryString).assertIsDisplayed()
    }

    @Test
    fun WhenUrlAndCategoryAreValidTheVideoShouldBeSavedAndReturnToHomeScreen() {
        val appNameString = composeTestRule.activity.getString(R.string.app_name)
        createNewVideo(validUrl, validCategory1)
        sleep(500)
        composeTestRule.onNodeWithText(appNameString).assertIsDisplayed()
    }

    @Test
    fun WhenUrlIsInvalidShouldShowSnackBar() {
        val snackBarString = composeTestRule.activity.getString(R.string.ERROR_INVALID_FIELDS)
        createNewVideo(invalidUrl, validCategory1)
        composeTestRule.onAllNodesWithText(snackBarString)[0].assertIsDisplayed()
    }

    @Test
    fun WhenBothFieldsAreEmptyShouldShowSnackBar() {
        val snackBarString = composeTestRule.activity.getString(R.string.ERROR_INVALID_FIELDS)
        createNewVideo(emptyString, emptyString)
        composeTestRule.onAllNodesWithText(snackBarString)[0].assertIsDisplayed()
    }

    @Test
    fun WhenCategoryIsEmptyShouldShowSnackBar() {
        val snackBarString = composeTestRule.activity.getString(R.string.ERROR_INVALID_FIELDS)
        createNewVideo(validUrl, emptyString)
        composeTestRule.onAllNodesWithText(snackBarString)[0].assertIsDisplayed()
    }

    @Test
    fun WhenCreatingTwoVideosWithDifferentCategoriesShouldDisplayTwoCategoryIcons() {
        clearDatabase()
        sleep(500)
        val videoCategoryClickableString = composeTestRule.activity.getString(R.string.video_category_clickable)
        createNewVideo(validUrl, validCategory1)
        sleep(500)
        createNewVideo(validUrl, validCategory2)
        sleep(500)
        composeTestRule.onAllNodesWithTag(videoCategoryClickableString).assertCountEquals(2)
    }

    @Test
    fun WhenCreatingTwoVideosWithSameCategoryShouldDisplayOneCategoryIcon() {
        clearDatabase()
        val videoCategoryClickableString = composeTestRule.activity.getString(R.string.video_category_clickable)
        createNewVideo(validUrl, validCategory1)
        sleep(500)
        createNewVideo(validUrl, validCategory1)
        sleep(500)
        composeTestRule.onAllNodesWithTag(videoCategoryClickableString).assertCountEquals(1)
    }

    @Test
    fun WhenCreatingVideosShouldBeDisplayedOnVideoList() {
        clearDatabase()
        sleep(500)
        val videoCategoryString = composeTestRule.activity.getString(R.string.video_category)
        val videoCardString = composeTestRule.activity.getString(R.string.video_card)
        createNewVideo(validUrl, validCategory1)
        sleep(500)
        createNewVideo(validUrl, validCategory2)
        sleep(500)
        composeTestRule.onAllNodesWithTag(videoCategoryString).assertCountEquals(2)
        composeTestRule.onAllNodesWithTag(videoCardString).assertCountEquals(2)
    }

    private fun clearDatabase() {
        VideoDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().targetContext)
            .clearAllTables()
    }
}
