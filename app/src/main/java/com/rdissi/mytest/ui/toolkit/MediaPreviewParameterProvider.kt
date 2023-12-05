package com.rdissi.mytest.ui.toolkit

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rdissi.mytest.data.local.mock.MockDataSource
import com.rdissi.mytest.domain.model.Media

class MediaPreviewParameterProvider : PreviewParameterProvider<Media> {
    override val values = sequenceOf(
        MockDataSource.getFirstMockVideo(),
        MockDataSource.getFirstMockStory()
    )
}