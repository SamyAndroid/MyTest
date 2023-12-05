package com.rdissi.mytest.data.source

import com.rdissi.mytest.data.local.dao.CatalogDao
import com.rdissi.mytest.data.local.model.CatalogEntity
import javax.inject.Inject

class CatalogLocalDataSource @Inject constructor(
    private val catalogDao: CatalogDao
) {
    suspend fun getCatalogEntity() = CatalogEntity(
        videos = catalogDao.getVideos(),
        stories = catalogDao.getStories()
    )

    suspend fun isNotEmpty() = catalogDao.getVideos().isEmpty().not() && catalogDao.getStories().isEmpty().not()

    suspend fun updateCatalogEntity(catalogEntity: CatalogEntity) {
        catalogDao.updateCatalog(catalogEntity)
    }

    suspend fun getVideoEntityById(videoId: Int) = catalogDao.getVideoById(videoId)

    suspend fun getStoryEntityById(storyId: Int) = catalogDao.geStoryById(storyId)
}
