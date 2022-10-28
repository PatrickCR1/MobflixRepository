package com.example.mobflix.ui.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobflix.R
import com.example.mobflix.service.listener.APIListener
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class VideoRegistrationViewModel(
    private val videoRepository: VideoRepository,
    private val categoryRepository: CategoryRepository,
    private val context: Context
) : ViewModel() {

    val image = mutableStateOf("")

    private val _urlText = MutableLiveData<String>("")
    val urlText: LiveData<String> = _urlText

    private val _categoryText = MutableLiveData<String>("")
    val categoryText: LiveData<String> = _categoryText

    private val _registrationClick = MutableLiveData<Boolean>()
    val registrationClick: LiveData<Boolean> = _registrationClick

    private val _snackBar = MutableLiveData<String>("")
    val snackBar: LiveData<String> = _snackBar

    // API Listener
    val listener = object : APIListener<String> {
        override fun onSucess(result: String) {
            image.value = result
        }

        override fun onFailure(message: String?) {
            if (message == null) {
                _snackBar.value = context.getString(R.string.ERROR_UNEXPECTED)
            } else {
                _snackBar.value = message!!
            }
        }
    }

    fun videoRegistration() {
        if (registrationValidation()) {
            viewModelScope.launch {
                val videoId = getVideoId(urlText.value!!)
                getImage(videoId)

                val video = VideoModel(
                    url = urlText.value!!,
                    category = categoryText.value!!,
                    image = image.value
                )
                videoRepository.saveVideo(video)

                val category = CategoryModel(category = categoryText.value!!)
                categoryRepository.saveCategory(category)

                _registrationClick.value = true
            }
        } else {
            _snackBar.value = context.getString(R.string.ERROR_INVALID_FIELDS)
        }
    }

    private fun registrationValidation(): Boolean {
        return (isYoutubeUrl(urlText.value!!) && (categoryText.value != "" && categoryText.value != null))
    }

    fun onUrlTextChanged(text: String) {
        _urlText.value = text
        if (isYoutubeUrl(text)) {
            getVideoPreview()
        }
    }

    fun onCategoryChanged(text: String) {
        _categoryText.value = text
    }

    fun getVideoPreview() {
        viewModelScope.launch {
            val videoId = getVideoId(urlText.value!!)
            getImage(videoId)
        }
    }

    fun clickComplete() {
        _registrationClick.value = false
    }

    suspend fun getImage(id: String) {
        if (checkInternet(listener)) {
            videoRepository.getThumbnailImage(id, listener)
        }
    }

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

    fun checkImage(): Boolean {
        return (image.value != "")
    }

    fun showSnackBar() {
        _snackBar.value = context.getString(R.string.ERROR_INVALID_FIELDS)
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
