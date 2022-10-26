package com.example.mobflix.service.repository.remote

import com.example.mobflix.service.constants.MobflixConstants
import com.example.mobflix.service.model.api.TopResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("videos")
    suspend fun getVideo(
        @Query("key") key: String = MobflixConstants.API.API_KEY,
        @Query("part") part: String = "snippet",
        @Query("id") id: String
    ) : Response<TopResponse>
}