package com.rdissi.mytest.data.source

import com.rdissi.mytest.data.remote.model.CatalogJson
import com.rdissi.mytest.data.remote.service.CatalogApiService
import javax.inject.Inject

class CatalogRemoteDataSource @Inject constructor(
    private val catalogApiService: CatalogApiService,
) {
    suspend fun fetchCatalog(): CatalogJson = catalogApiService.getCatalog()
}
