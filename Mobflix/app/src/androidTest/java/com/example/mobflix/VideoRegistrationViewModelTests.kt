package com.example.mobflix

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import com.example.mobflix.ui.components.categoryListSample
import com.example.mobflix.ui.components.stringSample
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VideoRegistrationViewModelTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //Arrange - Config
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val categoryRepository = mockk<CategoryRepository>()
    val videoRepository = mockk<VideoRepository>()
    val viewModel = VideoRegistrationViewModel(videoRepository, categoryRepository, context)

    @Test
    fun validFieldsShouldCallRepositoryFunctionsAndChangeClickValue() = runBlocking {
        // Arrange
        coEvery { videoRepository.getThumbnailImage(any(), any()) } returns Unit
        coEvery { videoRepository.saveVideo(any()) } returns Unit
        coEvery { categoryRepository.saveCategory(any()) } returns Unit
        coEvery { categoryRepository.getCategoryList() } returns categoryListSample

        //Act
        viewModel.onUrlTextChanged("https://youtu.be/ijgYsmthKWU")
        viewModel.onCategoryChanged("Programming")
        viewModel.videoRegistration()

        //Assert

        coVerify {
            videoRepository.saveVideo(any())
            categoryRepository.saveCategory(any())
        }

        val returnValue = viewModel.registrationClick.getOrAwaitValue()
        assertEquals(true, returnValue)
    }

    @Test
    fun invalidFieldsShouldChangeSnackBarValue() = runBlocking {

        //Act
        viewModel.onUrlTextChanged("asdasdasd")
        viewModel.onCategoryChanged("")
        viewModel.videoRegistration()

        val returnValue = viewModel.snackBar.getOrAwaitValue()
        assertEquals(context.getString(R.string.ERROR_INVALID_FIELDS), returnValue)
    }

    @Test
    fun whenOnUrlTextChangedIsCalledShouldChangeTextValue() {
        val text = "Text"

        //Act
        viewModel.onUrlTextChanged(text)

        // Assert
        val returnValue = viewModel.urlText.getOrAwaitValue()
        assertEquals(text, returnValue)
    }

    @Test
    fun whenOnCategoryChangedIsCalledShouldChangeCategoryValue() {
        val text = "Text"

        //Act
        viewModel.onCategoryChanged(text)

        // Assert
        val returnValue = viewModel.categoryText.getOrAwaitValue()
        assertEquals(text, returnValue)
    }

    @Test
    fun whenFieldsAreYoutubeURLShouldCallGetVideoPreview() {
        // Arrange
        coEvery { videoRepository.getThumbnailImage(any(), any()) } returns Unit

        // Act
        viewModel.onUrlTextChanged("https://youtu.be/ijgYsmthKWU")

        // Assert
        coVerify {
            videoRepository.getThumbnailImage(any(), any())
        }
    }

    @Test
    fun whenClickCompleteIsCalledShouldChangeClickValueToFalse() {
        // Act
        viewModel.clickComplete()

        // Assert
        val returnValue = viewModel.registrationClick.getOrAwaitValue()
        assertEquals(false, returnValue)
    }

    @Test
    fun getVideoIdShouldReturnVideoId() {

        // Act
        val returnValue = viewModel.getVideoId("https://youtu.be/ijgYsmthKWU")

        // Assert
        assertEquals("ijgYsmthKWU", returnValue)
    }

    @Test
    fun whenIsYoutubeURLIsCalledWithValidURLShouldReturnTrue() {

        // Act
        val returnValue = viewModel.isYoutubeUrl("https://youtu.be/ijgYsmthKWU")

        // Assert
        assertTrue(returnValue)
    }

    @Test
    fun whenIsYoutubeURLIsCalledWithInvalidURLShouldReturnFalse() {

        // Act
        val returnValue = viewModel.isYoutubeUrl("jashdajshdhjsa")

        // Assert
        assertFalse(returnValue)
    }

    @Test
    fun callingCheckImageWithValidImageShouldReturnTrue() {

        // Act
        viewModel.listener.onSuccess("jashdajshdhjsa")
        val returnValue = viewModel.checkImage()

        // Assert
        assertTrue(returnValue)
    }

    @Test
    fun callingCheckImageWithEmptyImageShouldReturnFalse() {

        // Act
        viewModel.listener.onSuccess("")
        val returnValue = viewModel.checkImage()

        // Assert
        assertFalse(returnValue)
    }

    @Test
    fun checkCategoryShouldReturnTrue() = runBlocking {
        // Arrange
        coEvery {categoryRepository.getCategoryList()} returns categoryListSample

        // Act
        val returnedValue = viewModel.checkSave(stringSample)

        // Assert
        Assert.assertTrue(returnedValue)
    }

    @Test
    fun whenSavingCategoryWithSameNameCheckCategoryShouldReturnFalse() = runBlocking {
        // Arrange
        coEvery {categoryRepository.getCategoryList()} returns categoryListSample

        // Act
        val returnedValue = viewModel.checkSave("Mobile")

        // Assert
        Assert.assertFalse(returnedValue)
    }

    @Test
    fun whenShowSnackBarFunIsCalledSnackBarValueShouldChange() {

        // Act
        viewModel.showSnackBar()

        // Assert
        val returnValue = viewModel.snackBar.getOrAwaitValue()
        assertEquals(context.getString(R.string.ERROR_INVALID_FIELDS), returnValue)
    }
}
