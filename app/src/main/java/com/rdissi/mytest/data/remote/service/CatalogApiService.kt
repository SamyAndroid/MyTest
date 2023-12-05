package com.rdissi.mytest.data.remote.service

import com.rdissi.mytest.data.remote.model.CatalogJson
import retrofit2.Response
import retrofit2.http.GET

interface CatalogApiService {

    @GET("api/json-storage/bin/edfefba")
    suspend fun getCatalog(): CatalogJson?
}