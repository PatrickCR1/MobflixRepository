package com.example.mobflix.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobflix.service.constants.MobflixConstants
import com.example.mobflix.service.model.video.VideoDatabaseModel

@Database(entities = [VideoDatabaseModel::class], version = 1)
abstract class VideoDatabase() : RoomDatabase() {

    abstract val videoDAO: VideoDAO

    // Singleton
    companion object {

        fun getDatabase(context: Context): VideoDatabase {
            return synchronized(VideoDatabase::class) {
                Room.databaseBuilder(
                    context,
                    VideoDatabase::class.java,
                    MobflixConstants.BUILDER.VIDEO_DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}
