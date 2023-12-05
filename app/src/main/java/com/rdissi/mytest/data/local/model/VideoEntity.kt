package com.rdissi.mytest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val date: Double,
    val sport: SportEntity,
    val thumb: String,
    val url: String,
    val views: Int
)