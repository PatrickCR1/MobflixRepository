package com.example.mobflix.ui.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import com.example.mobflix.R
import com.example.mobflix.service.listener.APIListener
import java.util.regex.Pattern

abstract class BaseVideoViewModel(private val context: Context): ViewModel() {

    fun getVideoId(videoUrl: String): String {
        var videoId: String = "";
        val regex: String =
            "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(videoUrl)
        if (matcher.find()) {
            videoId = matcher.group(1);
        }
        return videoId;
    }

    fun isYoutubeUrl(url: String): Boolean {
        val pattern = Regex(
            "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)"
        )
        return (!url.isEmpty()) && (url.matches(pattern))
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
