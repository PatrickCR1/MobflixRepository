package com.example.mobflix.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobflix.service.model.category.CategoryDatabaseModel

@Dao
interface CategoryDAO {

    @Insert
    suspend fun save(categoryModel: CategoryDatabaseModel)

    @Query("SELECT * FROM Category")
    suspend fun list(): List<CategoryDatabaseModel>

    @Query("DELETE FROM Category WHERE category = :category")
    suspend fun remove(category: String)

    @Query("DELETE FROM Category")
    fun clear()
}
