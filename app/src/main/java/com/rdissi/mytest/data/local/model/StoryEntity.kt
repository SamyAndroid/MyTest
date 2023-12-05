package com.rdissi.mytest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories")
data class StoryEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val date: Double,
    val sport: SportEntity,
    val author: String,
    val image: String,
    val teaser: String
)