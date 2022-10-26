package com.example.mobflix.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobflix.service.model.category.CategoryDatabaseModel

@Dao
interface CategoryDAO {

    @Insert
    fun save(categoryModel: CategoryDatabaseModel)

    @Query("SELECT * FROM Category")
    fun list(): List<CategoryDatabaseModel>

    @Query("DELETE FROM Category")
    fun clear()
}
