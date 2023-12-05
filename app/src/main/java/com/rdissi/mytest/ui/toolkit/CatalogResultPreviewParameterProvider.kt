package com.rdissi.mytest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.mytest.ui.catalog.CatalogViewModel

class CatalogResultPreviewParameterProvider : PreviewParameterProvider<CatalogViewModel.UiState> {
    override val values = sequenceOf(
        CatalogViewModel.UiState.Loading,
        CatalogViewModel.UiState.Success(MockDataSource.getMockCatalogMixed()),
        CatalogViewModel.UiState.Error("An error occurred")
    )
}