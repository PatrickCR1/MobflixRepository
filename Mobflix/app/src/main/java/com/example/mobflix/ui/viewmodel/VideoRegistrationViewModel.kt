package com.example.mobflix.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mobflix.R
import com.example.mobflix.service.listener.APIListener
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import kotlinx.coroutines.launch

class VideoRegistrationViewModel(
    private val videoRepository: VideoRepository,
    private val categoryRepository: CategoryRepository,
    private val context: Context
) : BaseVideoViewModel(context) {

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
                if (checkSave(category.category)) {
                    categoryRepository.saveCategory(category)
                }

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

    private fun getVideoPreview() {
        viewModelScope.launch {
            val videoId = getVideoId(urlText.value!!)
            getImage(videoId)
        }
    }

    fun clickComplete() {
        _registrationClick.value = false
    }

    private suspend fun getImage(id: String) {
        if (checkInternet(listener)) {
            videoRepository.getThumbnailImage(id, listener)
        }
    }

    fun checkImage(): Boolean {
        return (image.value != "")
    }

    suspend fun checkSave(name: String): Boolean {
        val list = categoryRepository.getCategoryList()
        var add = true
        list.forEach {
            if (it.category == name) {
                add = false
            }
        }
        return add
    }

    fun showSnackBar() {
        _snackBar.value = context.getString(R.string.ERROR_INVALID_FIELDS)
    }
}
