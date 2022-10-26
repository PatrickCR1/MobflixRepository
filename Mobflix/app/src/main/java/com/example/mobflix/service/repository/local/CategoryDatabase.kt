package com.example.mobflix.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobflix.service.constants.MobflixConstants
import com.example.mobflix.service.model.category.CategoryDatabaseModel

@Database(entities = [CategoryDatabaseModel::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    abstract val categoryDAO: CategoryDAO

    // Singleton
    companion object {

        fun getDatabase(context: Context): CategoryDatabase {
            return synchronized(CategoryDatabase::class) {
                Room.databaseBuilder(
                    context,
                    CategoryDatabase::class.java,
                    MobflixConstants.BUILDER.CATEGORY_DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}
