package com.example.mobflix.service.listener

interface APIListener<T> {

    fun onSucess(result: T)

    fun onFailure(message: String)

}