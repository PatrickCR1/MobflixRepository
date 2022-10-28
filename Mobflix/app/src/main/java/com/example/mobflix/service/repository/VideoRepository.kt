package com.example.mobflix.service.repository

import androidx.compose.ui.graphics.Color
import com.example.mobflix.service.constants.MobflixConstants
import com.example.mobflix.service.listener.APIListener
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.service.repository.local.VideoDAO
import com.example.mobflix.service.repository.remote.YoutubeService
import com.example.mobflix.toVideoDatabaseModel
import com.example.mobflix.toVideoModel
import com.google.gson.Gson

class VideoRepository(
    private val dao: VideoDAO,
    private val webService: YoutubeService,
    private val categoryRepository: CategoryRepository,
) {

    suspend fun getThumbnailImage(videoId: String, listener: APIListener<String>) {
        val response = webService.getVideo(id = videoId)
        if (response.isSuccessful) {
            if (response.code() == MobflixConstants.HTTP.SUCCESS) {
                val imageUrl =
                    if (response.body()?.items?.count() != 0) {
                        response.body()?.items!![0].snippet?.thumbnails?.high?.thumbnailUrl!!
                    } else {
                        ""
                    }
                listener.onSucess(imageUrl)

            } else {
                listener.onFailure(failResponse(response.errorBody()!!.string()))
            }
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

    suspend fun saveVideo(video: VideoModel) {
        val videoDB = video.toVideoDatabaseModel()
        dao.save(videoDB)
    }

    fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }
}
