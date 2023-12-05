package com.rdissi.mytest.domain.model

data class Story(
    override val id: Int,
    val title: String,
    val date: Double,
    val sport: Sport,
    val author: String,
    val image: String,
    val teaser: String
) : Media(id)