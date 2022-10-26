package com.example.mobflix.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
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
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val image = mutableStateOf("")

    private val _urlText = MutableLiveData<String>("")
    val urlText: LiveData<String> = _urlText

    private val _categoryText = MutableLiveData<String>("")
    val categoryText: LiveData<String> = _categoryText

    private val _registrationClick = MutableLiveData<Boolean>()
    val registrationClick: LiveData<Boolean> = _registrationClick

    private val _showSnackBar = MutableLiveData<Boolean>()
    val showSnackBar: LiveData<Boolean> = _showSnackBar

    // API Listener
    val listener = object : APIListener<String> {
        override fun onSucess(result: String) {
            image.value = result
        }

        override fun onFailure(message: String) {
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
            _showSnackBar.value = true
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

    fun getCategoryColor(videoModel: VideoModel): Color {
        val list = categoryRepository.getCategoryList()
        var categoryColor = Color.Black
        list.forEach {
            if (videoModel.category == it.category) {
                categoryColor = it.color
            }
        }
        return categoryColor
    }

    fun clickComplete() {
        _registrationClick.value = false
    }

    suspend fun getImage(id: String) {
        videoRepository.getThumbnailImage(id, listener)
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
        _showSnackBar.value = true
    }
}
