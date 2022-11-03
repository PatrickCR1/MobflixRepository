package com.example.mobflix.service.repository

import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.repository.local.CategoryDAO
import com.example.mobflix.toCategoryDatabaseModel
import com.example.mobflix.toCategoryModel

class CategoryRepository(private val dao: CategoryDAO) {

    suspend fun getCategoryList(): List<CategoryModel> {
        val categoryDatabaseList = dao.list()
        val categoryList = categoryDatabaseList.map { it.toCategoryModel() }
        return categoryList
    }

    suspend fun saveCategory(category: CategoryModel) {
        val categoryDB = category.toCategoryDatabaseModel()
        dao.save(categoryDB)
    }

    suspend fun removeCategory(category: String) {
        dao.remove(category)
    }
}
