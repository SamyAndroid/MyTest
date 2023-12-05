package com.rdissi.mytest.domain.usecase

import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Sport
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.repository.CatalogRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class GetStoryUseCaseTest {

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Test
    fun `request Story from repository with Success`() = runTest {
        // Given
        val storyId = 123
        val expectedId = 123456
        val expectedTitle = "MyTest"
        val expectedAuthor = "Rdissi"
        val expectedImageUrl = "http://mon.image.com"
        val expectedTeaser = "blablabla"
        val expectedDate = 1588170827.457
        val expectedSportId = 5
        val expectedSportName = "football"

        val sportMock = mock<Sport> {
            on(it.id).thenReturn(expectedSportId)
            on(it.name).thenReturn(expectedSportName)
        }

        val storyMock = mock<Story> {
            on(it.id).thenReturn(expectedId)
            on(it.title).thenReturn(expectedTitle)
            on(it.author).thenReturn(expectedAuthor)
            on(it.image).thenReturn(expectedImageUrl)
            on(it.date).thenReturn(expectedDate)
            on(it.teaser).thenReturn(expectedTeaser)
            on(it.sport).thenReturn(sportMock)
        }
        given(catalogRepository.getStoryById(storyId = storyId)).willReturn(storyMock)

        //When
        val result: Result<Story> = GetStoryUseCase(catalogRepository).invoke(storyId)

        //Then
        assertTrue(result is Result.Success)
        assertEquals(expectedId, (result as Result.Success).data.id)
        assertEquals(expectedTitle, result.data.title)
        assertEquals(expectedAuthor, result.data.author)
        assertEquals(expectedImageUrl, result.data.image)
        assertEquals(expectedTeaser, result.data.teaser)
        assertEquals(expectedDate, result.data.date, 0.0)
        assertEquals(expectedSportId, result.data.sport.id)
        assertEquals(expectedSportName, result.data.sport.name)
    }

    @Test
    fun `request Story from repository with Error when story is null`() = runTest {
        // Given
        val storyId = 123
        given(catalogRepository.getStoryById(storyId = storyId)).willReturn(null)

        //When
        val result: Result<Story> = GetStoryUseCase(catalogRepository).invoke(storyId)

        //Then
        assertTrue(result is Result.Error)
    }
}