package com.example.mobflix.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobflix.service.model.video.VideoDatabaseModel

@Dao
interface VideoDAO {

    @Insert
    suspend fun save(video: VideoDatabaseModel)

    @Query("SELECT * FROM Video")
    suspend fun videoList(): List<VideoDatabaseModel>

    @Query("SELECT * FROM Video WHERE category = :category")
    suspend fun filteredVideoList(category: String): List<VideoDatabaseModel>

    @Query("DELETE FROM Video WHERE id = :id")
    suspend fun remove(id: Int)

    @Update
    suspend fun updateVideo(video: VideoDatabaseModel)

    @Query("DELETE FROM Video")
    fun clear()
}