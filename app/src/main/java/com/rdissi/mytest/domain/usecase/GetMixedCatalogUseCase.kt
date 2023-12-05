package com.rdissi.mytest.domain.usecase

import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Catalog
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.mix
import com.rdissi.mytest.domain.model.sortedByDescending
import com.rdissi.mytest.domain.repository.CatalogRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMixedCatalogUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    operator fun invoke(): Flow<Result<List<Media>>> = flow {
        emit(Result.Loading)
        runCatching {
            val catalog: Catalog? = catalogRepository.getCatalog()
            catalog?.sortedByDescending()?.mix() ?: throw Exception("Catalog null")
        }.onSuccess { medias ->
            emit(Result.Success(medias))
        }.onFailure {
            emit(Result.Error("Error=$it"))
            //Log.e("GetMixedCatalogUseCase", it.toString())
        }
    }
}
