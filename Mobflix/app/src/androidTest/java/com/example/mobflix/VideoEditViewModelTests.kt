package com.example.mobflix

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import com.example.mobflix.ui.components.videoModelSample
import com.example.mobflix.ui.viewmodel.VideoEditViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VideoEditViewModelTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //Arrange - Config
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val categoryRepository = mockk<CategoryRepository>()
    val videoRepository = mockk<VideoRepository>()
    val viewModel = VideoEditViewModel(videoRepository, categoryRepository, context)
    val videoModel = videoModelSample

    @Test
    fun whenSetVideoModelIsCalledShouldChangeLiveDataValues() {
        // Act
        viewModel.setVideoModel(videoModel)

        // Assert
        val returnUrlValue = viewModel.urlText.getOrAwaitValue()
        val returnCategoryValue = viewModel.categoryText.getOrAwaitValue()

        Assert.assertEquals(videoModel.url, returnUrlValue)
        Assert.assertEquals(videoModel.category, returnCategoryValue)
    }

    @Test
    fun validFieldsShouldCallRepositoryUpdateFunctionsAndChangeClickValue() = runBlocking {
        // Arrange
        coEvery { videoRepository.getThumbnailImage(any(), any()) } returns Unit
        coEvery { videoRepository.updateVideo(any()) } returns Unit
        coEvery { categoryRepository.saveCategory(any()) } returns Unit

        //Act
        viewModel.setVideoModel(videoModel)
        viewModel.onUrlTextChanged("https://youtu.be/ijgYsmthKWU")
        viewModel.onCategoryChanged("Mobile")
        viewModel.videoUpdate()

        //Assert

        coVerify {
            videoRepository.updateVideo(any())
            categoryRepository.saveCategory(any())
        }

        val returnValue = viewModel.editButtonClick.getOrAwaitValue()
        Assert.assertEquals(true, returnValue)
    }

    @Test
    fun buttonClickShouldCallRepositoryRemoveFunctionsAndChangeClickValue() = runBlocking {
        // Arrange
        coEvery { videoRepository.removeVideo(any()) } returns Unit
        coEvery { videoRepository.checkDelete(any()) } returns false

        //Act
        viewModel.setVideoModel(videoModel)
        viewModel.videoRemove()

        //Assert
        coVerify {
            videoRepository.removeVideo(any())
        }

        val returnValue = viewModel.deleteButtonClick.getOrAwaitValue()
        Assert.assertEquals(true, returnValue)
    }

    @Test
    fun deleteCategoryShouldCallDaoDeleteIfNoVideoExists() = runBlocking {
        // Arrange
        coEvery { categoryRepository.removeCategory(any()) } returns Unit
        coEvery { videoRepository.removeVideo(any()) } returns Unit
        coEvery { videoRepository.checkDelete(any()) } returns true


        // Act
        viewModel.setVideoModel(videoModel)
        viewModel.videoRemove()

        // Assert
        coVerify() {
            categoryRepository.removeCategory(any())
        }
    }

    @Test
    fun deleteCategoryShouldNotCallDaoDeleteIfVideoExists() = runBlocking {
        // Arrange
        coEvery { categoryRepository.removeCategory(any()) } returns Unit
        coEvery { videoRepository.removeVideo(any()) } returns Unit
        coEvery { videoRepository.checkDelete(any()) } returns false


        // Act
        viewModel.setVideoModel(videoModel)
        viewModel.videoRemove()

        // Assert
        coVerify(inverse = true) {
            categoryRepository.removeCategory(any())
        }
    }

    @Test
    fun invalidFieldsShouldChangeSnackBarValue() = runBlocking {

        //Act
        viewModel.onUrlTextChanged("asdasdasd")
        viewModel.onCategoryChanged("")
        viewModel.videoUpdate()

        val returnValue = viewModel.snackBar.getOrAwaitValue()
        Assert.assertEquals(context.getString(R.string.ERROR_INVALID_FIELDS), returnValue)
    }

    @Test
    fun whenOnUrlTextChangedIsCalledShouldChangeTextValue() {
        val text = "Text"

        //Act
        viewModel.onUrlTextChanged(text)

        // Assert
        val returnValue = viewModel.urlText.getOrAwaitValue()
        Assert.assertEquals(text, returnValue)
    }

    @Test
    fun whenOnCategoryChangedIsCalledShouldChangeCategoryValue() {
        val text = "Text"

        //Act
        viewModel.onCategoryChanged(text)

        // Assert
        val returnValue = viewModel.categoryText.getOrAwaitValue()
        Assert.assertEquals(text, returnValue)
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
    fun whenClickEditCompleteIsCalledShouldChangeClickValueToFalse() {
        // Act
        viewModel.clickEditComplete()

        // Assert
        val returnEditValue = viewModel.editButtonClick.getOrAwaitValue()
        Assert.assertEquals(false, returnEditValue)
    }

    @Test
    fun whenClickDeleteCompleteIsCalledShouldChangeClickValueToFalse() {
        // Act
        viewModel.clickDeleteComplete()

        // Assert
        val returnDeleteValue = viewModel.deleteButtonClick.getOrAwaitValue()
        Assert.assertEquals(false, returnDeleteValue)
    }

    @Test
    fun getVideoIdShouldReturnVideoId() {

        // Act
        val returnValue = viewModel.getVideoId("https://youtu.be/ijgYsmthKWU")

        // Assert
        Assert.assertEquals("ijgYsmthKWU", returnValue)
    }

    @Test
    fun whenIsYoutubeURLIsCalledWithValidURLShouldReturnTrue() {

        // Act
        val returnValue = viewModel.isYoutubeUrl("https://youtu.be/ijgYsmthKWU")

        // Assert
        Assert.assertTrue(returnValue)
    }

    @Test
    fun whenIsYoutubeURLIsCalledWithInvalidURLShouldReturnFalse() {

        // Act
        val returnValue = viewModel.isYoutubeUrl("jashdajshdhjsa")

        // Assert
        Assert.assertFalse(returnValue)
    }

    @Test
    fun callingCheckImageWithValidImageShouldReturnTrue() {

        // Act
        viewModel.listener.onSucess("jashdajshdhjsa")
        val returnValue = viewModel.checkImage()

        // Assert
        Assert.assertTrue(returnValue)
    }

    @Test
    fun callingCheckImageWithEmptyImageShouldReturnFalse() {

        // Act
        viewModel.listener.onSucess("")
        val returnValue = viewModel.checkImage()

        // Assert
        Assert.assertFalse(returnValue)
    }

    @Test
    fun whenShowSnackBarFunIsCalledSnackBarValueShouldChange() {

        // Act
        viewModel.showSnackBar()

        // Assert
        val returnValue = viewModel.snackBar.getOrAwaitValue()
        Assert.assertEquals(context.getString(R.string.ERROR_INVALID_FIELDS), returnValue)
    }
}
