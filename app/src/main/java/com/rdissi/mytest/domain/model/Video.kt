package com.rdissi.mytest.domain.model

data class Video(
    override val id: Int,
    val title: String,
    val date: Double,
    val sport: Sport,
    val thumb: String,
    val url: String,
    val views: Int
) : Media(id)
