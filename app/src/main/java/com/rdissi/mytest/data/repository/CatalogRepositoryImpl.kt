package com.rdissi.mytest.data.repository

import com.rdissi.mytest.data.source.CatalogLocalDataSource
import com.rdissi.mytest.data.source.CatalogRemoteDataSource
import com.rdissi.mytest.data.source.EntityConverter.toCatalog
import com.rdissi.mytest.data.source.EntityConverter.toCatalogEntity
import com.rdissi.mytest.data.source.EntityConverter.toStory
import com.rdissi.mytest.data.source.EntityConverter.toVideo
import com.rdissi.mytest.data.source.JsonConverter.toCatalog
import com.rdissi.mytest.di.IoDispatcher
import com.rdissi.mytest.domain.model.Catalog
import com.rdissi.mytest.domain.repository.CatalogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val catalogRemoteDataSource: CatalogRemoteDataSource,
    private val catalogLocalDataSource: CatalogLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CatalogRepository {

    override suspend fun getCatalog() = withContext(dispatcher) {
        try {
            val catalog: Catalog? = catalogRemoteDataSource.fetchCatalog()?.toCatalog()
            if (catalog != null) {
                updateCatalogLocalDataSource(catalog)
                catalog
            } else {
                fallbackOnCatalogInDatabase()
            }
        } catch (exception: Exception) {
            fallbackOnCatalogInDatabase() ?: throw exception
        }
    }

    private suspend fun fallbackOnCatalogInDatabase(): Catalog? {
        return if (catalogLocalDataSource.isNotEmpty()) {
            catalogLocalDataSource.getCatalogEntity().toCatalog()
        } else {
            null
        }
    }

    // TODO: need WS for recover the most recent video, video is retrieved in DB at this moment
    override suspend fun getVideoById(videoId: Int) = withContext(dispatcher) {
        catalogLocalDataSource.getVideoEntityById(videoId).toVideo()
    }

    override suspend fun getStoryById(storyId: Int) = withContext(dispatcher) {
        catalogLocalDataSource.getStoryEntityById(storyId).toStory()
    }

    private suspend fun updateCatalogLocalDataSource(catalog: Catalog) {
        catalogLocalDataSource.updateCatalogEntity(catalog.toCatalogEntity())
    }
}
