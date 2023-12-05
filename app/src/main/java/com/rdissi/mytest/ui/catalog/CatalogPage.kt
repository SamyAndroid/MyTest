package com.rdissi.mytest.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.ui.catalog.CatalogViewModel.UiState
import com.rdissi.mytest.ui.component.ResultList
import com.rdissi.mytest.ui.error.ErrorScreen
import com.rdissi.mytest.ui.toolkit.CatalogResultPreviewParameterProvider

@Composable
fun CatalogPage(
    modifier: Modifier = Modifier,
    catalogViewModel: CatalogViewModel = hiltViewModel(),
    onItemClick: (Media) -> Unit = {},
) {
    val uiState by catalogViewModel.uiState.collectAsStateWithLifecycle()
    CatalogResult(modifier, uiState, onItemClick)
}

@Composable
private fun CatalogResult(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onItemClick: (Media) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (uiState) {
            UiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is UiState.Success -> {
                uiState.medias?.let { list ->
                    ResultList(
                        medias = list,
                        onItemClick = onItemClick,
                    )
                }
            }
            is UiState.Error -> {
                ErrorScreen(uiState.message ?: "error")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogResultPreview(
    @PreviewParameter(CatalogResultPreviewParameterProvider::class) uiState: UiState
) {
    CatalogResult(uiState = uiState)
}
