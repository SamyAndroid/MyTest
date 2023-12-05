package com.rdissi.mytest.data.remote.model

data class StoryJson(
    val id: Int,
    val title: String,
    val date: Double,
    val sport: SportJson,
    val author: String,
    val image: String,
    val teaser: String
)