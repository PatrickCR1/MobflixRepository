package com.example.mobflix

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import com.example.mobflix.ui.components.categoryListSample
import com.example.mobflix.ui.components.videoListSample
import com.example.mobflix.ui.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTests {

    lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = MainViewModel(videoRepository, categoryRepository)
    }

    //Arrange - Config
    val categoryRepository = mockk<CategoryRepository>()
    val videoRepository = mockk<VideoRepository>()

    @Test
    fun shouldChangeVideoListValueWhenCallingGetVideoList() = runBlocking {

        // Arrange
        coEvery {videoRepository.getVideoList()} returns videoListSample

        //Act
        viewModel.getVideoList()

        val returnValue = viewModel.videoList.getOrAwaitValue()

        //Assert
        assertEquals(videoListSample, returnValue)

    }


    @Test
    fun shouldChangeCategoryListValueWhenCallingGetCategoryList() = runBlocking {

        // Arrange
        coEvery { categoryRepository.getCategoryList() } returns categoryListSample

        // Act
        viewModel.getCategoryList()

        val returnValue = viewModel.categoryList.getOrAwaitValue()

        // Assert
        assertEquals(categoryListSample, returnValue)
    }

    @Test
    fun shouldChangeFabClickValueToTrueWhenCallingNavigation() {
        //Act
        viewModel.navigationRegistrationScreen()

        //Assert
        viewModel.fabClick.observeForever{
            assertEquals(true, it)
        }
    }

    @Test
    fun shouldChangeFabClickValueToFalseWhenCallingNavigationComplete() {
        //Act
        viewModel.navigationRegistrationScreenComplete()

        //Assert
        viewModel.fabClick.observeForever{
            assertEquals(false, it)
        }
    }
}
