package com.arwani.ahmad.glimovie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.arwani.ahmad.glimovie.data.network.response.Genre
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val ogTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "page")
    var page: Int,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids")
    val genres: List<Int>,
)

class Converters {

    @TypeConverter
    fun listToJson(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}