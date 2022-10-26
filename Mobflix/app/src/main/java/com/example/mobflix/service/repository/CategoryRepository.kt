package com.example.mobflix.service.repository

import android.content.Context
import com.example.mobflix.service.model.category.CategoryModel
import com.example.mobflix.service.repository.local.CategoryDAO
import com.example.mobflix.toCategoryDatabaseModel
import com.example.mobflix.toCategoryModel

class CategoryRepository(private val dao: CategoryDAO, private val context: Context) {

    fun getCategoryList(): List<CategoryModel> {
        val categoryDatabaseList = dao.list()
        val categoryList = categoryDatabaseList.map {it.toCategoryModel()}
        return categoryList
    }

    fun saveCategory(category: CategoryModel) {
        if (checkSave(category.category)) {
            val categoryDB = category.toCategoryDatabaseModel()
            dao.save(categoryDB)
        }
    }

    fun checkSave(name: String): Boolean {
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
