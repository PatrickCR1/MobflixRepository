package com.example.mobflix

import androidx.compose.ui.graphics.Color
import com.example.mobflix.service.listener.APIListener
import com.example.mobflix.service.model.api.*
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import com.example.mobflix.service.repository.local.VideoDAO
import com.example.mobflix.service.repository.remote.YoutubeService
import com.example.mobflix.ui.components.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class VideoRepositoryTests {

    lateinit var videoRepository: VideoRepository

    @Before
    fun setup() {
        videoRepository = VideoRepository(videoDao, webService, categoryRepository)
    }

    val videoDao = mockk<VideoDAO>()
    val categoryRepository = mockk<CategoryRepository>()
    val webService = mockk<YoutubeService>()

    val id: String = ""
    val listener = mockk<APIListener<String>>()

    val mockTopResponse = mockk<TopResponse>()
    val zeroItemList = listOf<Item>()

    @Test
    fun getVideoListShouldCallDaoList() = runBlocking {
        // Arrange
        coEvery { videoDao.videoList() } returns videoDatabaseListSample
        coEvery { categoryRepository.getCategoryList() } returns categoryListSample

        val list = categoryRepository.getCategoryList()

        // Act
        val returnedValue = videoRepository.getVideoList()
        val videoList = videoDatabaseListSample.map { videoDBModel ->
            var videoColor = Color.Cyan
            list.forEach {
                if (videoDBModel.category == it.category) {
                    videoColor = it.color
                }
            }
            videoDBModel.toVideoModel(videoColor)
        }
        // Assert
        Assert.assertEquals(videoList, returnedValue)
        coVerify {
            videoDao.videoList()
        }
    }

    @Test
    fun saveVideoShouldCallDaoSave() = runBlocking {
        // Arrange
        coEvery { videoDao.videoList() } returns videoDatabaseListSample
        coEvery { videoDao.save(any()) } returns Unit

        // Act
        videoRepository.saveVideo(videoModelSample)

        // Assert
        coVerify {
            videoDao.save(any())
        }
    }

    @Test
    fun whenGetThumnailImageResponseIsSucessfulAndCodeIs200AndItemListIsNotZero() = runBlocking {
        // Arrange
        val thumbnailUrl = thumbnailSample
        val highResolution = HighResolution(thumbnailUrl)
        val thumbnails = Thumbnails(highResolution)
        val snippet = Snippet(thumbnails)
        val item = Item(snippet)
        val listItems = listOf<Item>(item)
        val topResponse = TopResponse(listItems)

        coEvery { webService.getVideo(any(), any(), any()) } returns Response.success(topResponse)
        coEvery { listener.onSucess(any()) } returns Unit

        // Act
        videoRepository.getThumbnailImage(id, listener)

        // Assert
        verify {
            listener.onSucess(thumbnailSample)
        }
    }

    @Test
    fun whenGetThumnailImageResponseIsSucessfulAndCodeIs200AndItemListIsZero() = runBlocking {
        // Arrange
        coEvery { webService.getVideo(any(), any(), any()) } returns Response.success(
            mockTopResponse
        )
        coEvery { mockTopResponse.items } returns zeroItemList
        coEvery { listener.onSucess(any()) } returns Unit

        // Act
        videoRepository.getThumbnailImage(id, listener)

        // Assert
        verify {
            listener.onSucess("")
        }
    }

    @Test
    fun whenGetThumnailImageResponseIsSucessfulAndCodeIsNot200() = runBlocking {
        // Arrange
        coEvery { webService.getVideo(any(), any(), any()) } returns Response.success(
            205,
            mockTopResponse
        )
        coEvery { mockTopResponse.items } returns zeroItemList
        coEvery { listener.onFailure(any()) } returns Unit

        // Act
        videoRepository.getThumbnailImage(id, listener)

        // Assert
        verify {
            listener.onFailure(null)
        }
    }

    @Test
    fun whenGetThumnailImageResponseIsNotSucessful() = runBlocking {
        // Arrange
        coEvery { webService.getVideo(any(), any(), any()) } returns Response.error(404, mockk())
        coEvery { listener.onFailure(null) } returns Unit

        // Act
        videoRepository.getThumbnailImage(id, listener)

        // Assert
        verify {
            listener.onFailure(null)
        }
    }
}