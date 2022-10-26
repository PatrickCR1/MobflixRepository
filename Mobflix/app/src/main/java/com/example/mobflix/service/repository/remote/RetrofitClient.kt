package com.example.mobflix.service.repository.remote

import com.example.mobflix.service.constants.MobflixConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object {
        fun getHttpClientInstance(): OkHttpClient {
            return OkHttpClient.Builder()
                .build()
        }

        fun getRetrofitInstance(httpClient: OkHttpClient): Retrofit {

            return synchronized(RetrofitClient::class) {
                Retrofit.Builder()
                    .baseUrl(MobflixConstants.API.BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }
}
