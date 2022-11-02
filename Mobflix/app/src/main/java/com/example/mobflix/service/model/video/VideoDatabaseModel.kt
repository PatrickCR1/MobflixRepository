package com.example.mobflix.service.model.video

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Video")
data class VideoDatabaseModel(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String = "",

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String = "",

    @SerializedName("category")
    @ColumnInfo(name = "category")
    val category: String = "",

    @SerializedName("favorite")
    @ColumnInfo(name = "favorite")
    val favorite: Int = 0,


) : Parcelable
