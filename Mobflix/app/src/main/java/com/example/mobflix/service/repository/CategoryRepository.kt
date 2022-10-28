package com.example.mobflix.service.repository

import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.repository.local.CategoryDAO
import com.example.mobflix.toCategoryDatabaseModel
import com.example.mobflix.toCategoryModel

class CategoryRepository(private val dao: CategoryDAO) {

    suspend fun getCategoryList(): List<CategoryModel> {
        val categoryDatabaseList = dao.list()
        val categoryList = categoryDatabaseList.map {it.toCategoryModel()}
        return categoryList
    }

    suspend fun saveCategory(category: CategoryModel) {
        if (checkSave(category.category)) {
            val categoryDB = category.toCategoryDatabaseModel()
            dao.save(categoryDB)
        }
    }

    suspend fun checkSave(name: String): Boolean {
        val list = getCategoryList()
        var add = true
        list.forEach {
            if (it.category == name) {
                add = false
            }
        }
        return add
    }
}
