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

class VideoEditViewModel(
    private val videoRepository: VideoRepository,
    private val categoryRepository: CategoryRepository,
    private val context: Context
) : BaseVideoViewModel(context) {

    val image = mutableStateOf("")

    private lateinit var videoModel: VideoModel

    private val _urlText = MutableLiveData<String>("")
    val urlText: LiveData<String> = _urlText

    private val _categoryText = MutableLiveData<String>("")
    val categoryText: LiveData<String> = _categoryText

    private val _editButtonClick = MutableLiveData<Boolean>()
    val editButtonClick: LiveData<Boolean> = _editButtonClick

    private val _deleteButtonClick = MutableLiveData<Boolean>()
    val deleteButtonClick: LiveData<Boolean> = _deleteButtonClick

    private val _snackBar = MutableLiveData<String>("")
    val snackBar: LiveData<String> = _snackBar

    // API Listener
    val listener = object : APIListener<String> {
        override fun onSuccess(result: String) {
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

    fun setVideoModel(video: VideoModel) {
        videoModel = video
        _urlText.value = videoModel.url
        _categoryText.value = videoModel.category
    }

    fun videoUpdate() {
        if (registrationValidation()) {
            viewModelScope.launch {
                val videoId = getVideoId(urlText.value!!)
                getImage(videoId)

                val video = VideoModel(
                    id = videoModel.id,
                    url = urlText.value!!,
                    category = categoryText.value!!,
                    image = image.value
                )
                videoRepository.updateVideo(video)

                val category = CategoryModel(category = categoryText.value!!)
                if (checkSave(category.category)) {
                    categoryRepository.saveCategory(category)
                }

                if (checkDelete(videoModel.category)) {
                    categoryRepository.removeCategory(videoModel.category)
                }

                _editButtonClick.value = true
            }
        } else {
            _snackBar.value = context.getString(R.string.ERROR_INVALID_FIELDS)
        }
    }

    fun videoRemove() {
        viewModelScope.launch {
            videoRepository.removeVideo(videoModel.id)

            if (checkDelete(videoModel.category)) {
                categoryRepository.removeCategory(videoModel.category)
            }

            _deleteButtonClick.value = true
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

    fun clickEditComplete() {
        _editButtonClick.value = false
    }

    fun clickDeleteComplete() {
        _deleteButtonClick.value = false
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

    suspend fun checkDelete(category: String): Boolean {
        val list = videoRepository.getFilteredVideoList(category)
        return list.isEmpty()
    }

    fun showSnackBar() {
        _snackBar.value = context.getString(R.string.ERROR_INVALID_FIELDS)
    }
}
