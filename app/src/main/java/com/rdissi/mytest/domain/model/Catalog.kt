package com.rdissi.mytest.domain.model

import com.rdissi.mytest.common.CollectionUtils

data class Catalog(
    val stories: List<Story>,
    val videos: List<Video>
)

fun Catalog.sortedByDescending() : Catalog = copy (
    stories = stories.sortedByDescending { it.date },
    videos = videos.sortedByDescending { it.date }
)


fun Catalog.mix(): List<Media> {
    return CollectionUtils.combine(videos, stories)
}