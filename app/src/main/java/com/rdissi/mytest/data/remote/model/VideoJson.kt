package com.rdissi.mytest.data.remote.model

data class VideoJson(
    val id: Int,
    val title: String,
    val date: Double,
    val sport: SportJson,
    val thumb: String,
    val url: String,
    val views: Int
)
