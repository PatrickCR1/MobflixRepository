package com.example.mobflix

import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.local.CategoryDAO
import com.example.mobflix.ui.components.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoryRepositoryTests {

    lateinit var categoryRepository: CategoryRepository

    @Before
    fun setup() {
        categoryRepository = CategoryRepository(dao)
    }

    val dao = mockk<CategoryDAO>()

    @Test
    fun getCategoryListShouldCallDaoList() = runBlocking {
        // Arrange
        coEvery {dao.list()} returns categoryDatabaseListSample

        // Act
        val returnedValue = categoryRepository.getCategoryList()
        val categoryList = categoryDatabaseListSample.map {it.toCategoryModel()}

        // Assert
        Assert.assertEquals(categoryList, returnedValue)
        coVerify {
            dao.list()
        }
    }

    @Test
    fun checkCategoryShouldReturnTrue() = runBlocking {
        // Arrange
        coEvery {dao.list()} returns categoryDatabaseListSample

        // Act
        val returnedValue = categoryRepository.checkSave("String")

        // Assert
        Assert.assertTrue(returnedValue)
    }

    @Test
    fun deleteCategoryShouldCallDaoDelete() = runBlocking {
        // Arrange
        coEvery { dao.remove(any()) } returns Unit

        // Act
        categoryRepository.removeCategory(stringSample)

        // Assert
        coVerify {
            dao.remove(any())
        }
    }

    @Test
    fun saveCategoryShouldCallDaoSave() = runBlocking {
        // Arrange
        coEvery {dao.list()} returns categoryDatabaseListSample
        coEvery {dao.save(any())} returns Unit

        // Act
        categoryRepository.saveCategory(categoryModelSample2)

        // Assert
        coVerify {
            dao.save(any())
        }
    }

    @Test
    fun saveCategoryWithSameNameShouldNotCallDaoSave() = runBlocking {
        // Arrange
        coEvery {dao.list()} returns categoryDatabaseListSample
        coEvery {dao.save(any())} returns Unit

        // Act
        categoryRepository.saveCategory(categoryModelSample)

        // Assert
        coVerify(inverse = true) {
            dao.save(any())
        }
    }
}
