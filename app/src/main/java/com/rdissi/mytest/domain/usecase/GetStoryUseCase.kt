package com.rdissi.mytest.domain.usecase

import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.repository.CatalogRepository
import javax.inject.Inject

class GetStoryUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    suspend operator fun invoke(storyId: Int): Result<Story> {
        return try {
            val story: Story = catalogRepository.getStoryById(storyId) ?: throw Exception("Story null")
            Result.Success(story)
        } catch (e: Exception) {
            Result.Error(e.message?: "GetStoryUseCase error")
        }
    }
}
