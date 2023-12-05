package com.rdissi.mytest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.mytest.ui.story.StoryViewModel

class StoryResultPreviewParameterProvider : PreviewParameterProvider<StoryViewModel.UiState> {
    override val values = sequenceOf(
        StoryViewModel.UiState.Loading,
        StoryViewModel.UiState.Success(MockDataSource.getFirstMockStory()),
        StoryViewModel.UiState.Error("An error occurred")
    )
}