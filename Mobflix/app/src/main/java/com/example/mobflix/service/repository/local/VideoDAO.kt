package com.example.mobflix.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobflix.service.model.video.VideoDatabaseModel

@Dao
interface VideoDAO {

    @Insert
    suspend fun save(video: VideoDatabaseModel)

    @Query("SELECT * FROM Video")
    suspend fun videoList(): List<VideoDatabaseModel>

    @Query("DELETE FROM Video")
    fun clear()
}