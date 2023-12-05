package com.rdissi.mytest.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rdissi.mytest.ui.toolkit.MockDataSource
import com.rdissi.mytest.domain.model.Media

@Composable
fun ResultList(
    medias: List<Media>,
    onItemClick: (Media) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
    ) {
        LazyColumn {
            items(
                items = medias,
                key = { it.id }
            ) { media: Media ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    MediaCard(
                        media = media,
                        onItemClick = onItemClick,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultListPreview() {
    ResultList(
        medias = MockDataSource.getMockCatalogMixed(),
        onItemClick = {},
    )
}
