package com.rdissi.mytest.ui.catalog

import app.cash.turbine.test
import com.nhaarman.mockito_kotlin.given
import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.usecase.GetMixedCatalogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CatalogViewModelTest {

    @Mock
    private lateinit var getMixedCatalogUseCase: GetMixedCatalogUseCase

    private lateinit var catalogViewModel: CatalogViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `UI flow will return Success when useCase return a success flow`(): Unit = runTest {
        //Given
        val medias = mock<List<Media>>()
        val success = Result.Success(medias)
        val flow = flowOf(Result.Loading, success)

        given(getMixedCatalogUseCase.invoke()).willReturn(flow)
        catalogViewModel = CatalogViewModel(getMixedCatalogUseCase)

        getMixedCatalogUseCase().test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(medias), awaitItem())
            awaitComplete()
        }

        //Then
        catalogViewModel.uiState.test(timeout = 5.seconds) {
            assertEquals(CatalogViewModel.UiState.Success(medias), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `UI flow will return Error when useCase return an error flow`(): Unit = runTest {
        //Given
        val message = "An error has occured"
        val error = Result.Error(message)
        val flow = flowOf(Result.Loading, error)

        given(getMixedCatalogUseCase.invoke()).willReturn(flow)
        catalogViewModel = CatalogViewModel(getMixedCatalogUseCase)

        getMixedCatalogUseCase().test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(message), awaitItem())
            awaitComplete()
        }

        //Then
        catalogViewModel.uiState.test(timeout = 5.seconds) {
            assertEquals(CatalogViewModel.UiState.Error(message), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}