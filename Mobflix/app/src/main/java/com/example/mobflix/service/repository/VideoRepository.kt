package com.example.mobflix.service.repository

import androidx.compose.ui.graphics.Color
import com.example.mobflix.service.constants.MobflixConstants
import com.example.mobflix.service.listener.APIListener
import com.example.mobflix.service.model.api.TopResponse
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.service.repository.local.VideoDAO
import com.example.mobflix.service.repository.remote.YoutubeService
import com.example.mobflix.toVideoDatabaseModel
import com.example.mobflix.toVideoModel
import retrofit2.Response

class VideoRepository(
    private val dao: VideoDAO,
    private val webService: YoutubeService,
    private val categoryRepository: CategoryRepository,
) {

    suspend fun getThumbnailImage(videoId: String, listener: APIListener<String>) {
        val response = webService.getVideo(id = videoId)
        if (response.isSuccessful) {
            responseSucessful(response, listener)
        } else {
            listener.onFailure(null)
        }
    }

    private fun responseSucessful(
        response: Response<TopResponse>,
        listener: APIListener<String>
    ) {
        if (response.code() == MobflixConstants.HTTP.SUCCESS) {
            val imageUrl =
                if (response.body()?.items?.count() != 0) {
                    response.body()?.items!![0].snippet?.thumbnails?.high?.thumbnailUrl!!
                } else {
                    ""
                }
            listener.onSucess(imageUrl)

        } else {
            listener.onFailure(null)
        }
    }


    suspend fun getVideoList(): List<VideoModel> {
        val list = categoryRepository.getCategoryList()
        val videoDBList = dao.videoList()
        val videoList = videoDBList.map { videoDBModel ->
            var videoColor = Color.Cyan
            list.forEach {
                if (videoDBModel.category == it.category) {
                    videoColor = it.color
                }
            }
            videoDBModel.toVideoModel(videoColor)
        }
        return videoList
    }

    suspend fun getFilteredVideoList(category: String): List<VideoModel> {
        val list = categoryRepository.getCategoryList()
        val videoDBList = dao.filteredVideoList(category)
        val videoList = videoDBList.map { videoDBModel ->
            var videoColor = Color.Cyan
            list.forEach {
                if (videoDBModel.category == it.category) {
                    videoColor = it.color
                }
            }
            videoDBModel.toVideoModel(videoColor)
        }
        return videoList
    }

    suspend fun saveVideo(video: VideoModel) {
        val videoDB = video.toVideoDatabaseModel()
        dao.save(videoDB)
    }

    suspend fun removeVideo(videoId: Int) {
        dao.remove(videoId)
    }

    suspend fun updateVideo(video: VideoModel) {
        val videoDB = video.toVideoDatabaseModel()
        dao.updateVideo(videoDB)
    }

    suspend fun checkDelete(category: String): Boolean {
        val list = getFilteredVideoList(category)
        return list.isEmpty()
    }
}
