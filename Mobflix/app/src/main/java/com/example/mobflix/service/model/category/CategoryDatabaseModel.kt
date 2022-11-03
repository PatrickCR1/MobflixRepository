package com.example.mobflix.service.model.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class CategoryDatabaseModel(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @SerializedName("category")
    @ColumnInfo(name = "category")
    val category: String = "",

    @SerializedName("color")
    @ColumnInfo(name = "color")
    val color: Int = 0
    )
