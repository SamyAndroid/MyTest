package com.rdissi.mytest.domain.repository

import com.rdissi.mytest.domain.model.Catalog
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video

interface CatalogRepository {
    suspend fun getCatalog(): Catalog?
    suspend fun getVideoById(videoId: Int): Video?
    suspend fun getStoryById(storyId: Int): Story?
}
