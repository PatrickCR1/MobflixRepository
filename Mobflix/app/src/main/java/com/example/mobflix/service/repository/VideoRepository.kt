package com.example.mobflix.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.mobflix.R
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
    private val context: Context
) {

    suspend fun getThumbnailImage(videoId: String, listener: APIListener<String>) {
        checkInternet(listener)
        if (checkInternet(listener)) {
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
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        }
    }

    fun getVideoList(): List<VideoModel> {
        val videoDBList = dao.videoList()
        val videoList = videoDBList.map { it.toVideoModel() }
        return videoList
    }

    fun saveVideo(video: VideoModel) {
        val videoDB = video.toVideoDatabaseModel()
        dao.save(videoDB)
    }

    fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    // Check Internet
    fun isConnectionAvailable(): Boolean {

        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNet = cm.activeNetwork ?: return false
            val networkCapabilities = cm.getNetworkCapabilities(activeNet) ?: return false
            result = when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            // Deprecated
            if (cm.activeNetworkInfo != null) {
                result = when (cm.activeNetworkInfo!!.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }

    fun <T> checkInternet(listener: APIListener<T>): Boolean {
        return if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.OFFLINE_MODE))
            false
        } else true
    }
}
