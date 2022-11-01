package com.example.mobflix.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoRepository: VideoRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    // LiveData
    private val _videoList = MutableLiveData<List<VideoModel>>(mutableListOf())
    val videoList: LiveData<List<VideoModel>> = _videoList

    private val _categoryList = MutableLiveData<List<CategoryModel>>(mutableListOf())
    val categoryList: LiveData<List<CategoryModel>> = _categoryList

    private val _fabClick = MutableLiveData<Boolean>()
    val fabClick: LiveData<Boolean> = _fabClick

    private val _videoClick = MutableLiveData<VideoModel>()
    val videoClick: LiveData<VideoModel> = _videoClick

    // Get List
    fun getVideoList() {
        viewModelScope.launch {
            _videoList.value = videoRepository.getVideoList()
        }
    }

    fun getFilteredVideoList(category: String) {
        viewModelScope.launch {
            _videoList.value = videoRepository.getFilteredVideoList(category)
        }
    }

    fun getCategoryList() {
        viewModelScope.launch {
            _categoryList.value = categoryRepository.getCategoryList()
        }
    }

    fun navigationRegistrationScreen() {
        _fabClick.value = true
    }

    fun navigationRegistrationScreenComplete() {
        _fabClick.value = false
    }

    fun navigationEditScreen(video: VideoModel) {
        _videoClick.value = video
    }

    fun navigationEditScreenComplete() {
        _videoClick.value = VideoModel()
    }
}
