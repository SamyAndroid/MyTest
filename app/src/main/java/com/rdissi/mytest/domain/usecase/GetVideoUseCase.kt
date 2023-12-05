package com.rdissi.mytest.domain.usecase

import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video
import com.rdissi.mytest.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    suspend operator fun invoke(videoId: Int): Result<Video> {
        return try {
            val video: Video = catalogRepository.getVideoById(videoId) ?: throw Exception("video null")
            Result.Success(video)
        } catch (e: Exception) {
            Result.Error(e.message?: "GetVideoUseCase error")
        }
    }
}