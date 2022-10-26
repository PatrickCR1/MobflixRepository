package com.example.mobflix.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.model.video.VideoModel
import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository

class MainViewModel(private val videoRepository: VideoRepository, private val categoryRepository: CategoryRepository) : ViewModel() {

    // LiveData
    private val _videoList = MutableLiveData<List<VideoModel>>()
    val videoList: LiveData<List<VideoModel>> = _videoList

    private val _categoryList = MutableLiveData<List<CategoryModel>>()
    val categoryList: LiveData<List<CategoryModel>> = _categoryList

    private val _fabClick = MutableLiveData<Boolean>()
    val fabClick: LiveData<Boolean> = _fabClick

    // Get List
    fun getVideoList() {
        _videoList.value = videoRepository.getVideoList()
    }

    fun getCategoryList() {
        _categoryList.value = categoryRepository.getCategoryList()
    }

    fun navigation() {
        _fabClick.value = true
    }

    fun navigationComplete() {
        _fabClick.value = false
    }
}
