package com.example.mobflix.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _fabClick = MutableLiveData<Boolean>()
    val fabClick: LiveData<Boolean> = _fabClick

    fun navigation() {
        _fabClick.value = true
    }
}