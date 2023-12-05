package com.rdissi.mytest.domain.usecase

import app.cash.turbine.test
import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Catalog
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.sortedByDescending
import com.rdissi.mytest.domain.repository.CatalogRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class GetMixedCatalogUseCaseTest {

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Test
    fun `fetch catalog will return a flow on Success`() = runTest {
        // Given
        val catalogMock = mock<Catalog> {
            on(it.sortedByDescending()).thenReturn(mock())
        }
        given(catalogRepository.getCatalog()).willReturn(catalogMock)

        //Then
        GetMixedCatalogUseCase(catalogRepository).invoke().test{
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Media>()), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `fetch catalog null will return a flow on Error`() = runBlocking {
        //Given
        given(catalogRepository.getCatalog()).willReturn(null)

        //Then
        GetMixedCatalogUseCase(catalogRepository).invoke().test{
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error("Error=java.lang.Exception: Catalog null"), awaitItem())
            awaitComplete()
        }
    }

    /*
    @Test
    fun simpleTest() = runTest {
        flowOf("one", "two").test {
            assertEquals("one", awaitItem())
            assertEquals("two", awaitItem())
            awaitComplete()
        }
    }*/
}