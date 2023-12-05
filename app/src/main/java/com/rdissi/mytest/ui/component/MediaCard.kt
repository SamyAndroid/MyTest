package com.rdissi.mytest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video
import com.rdissi.mytest.ui.toolkit.MediaPreviewParameterProvider

@Composable
fun MediaCard(
    modifier: Modifier = Modifier,
    media: Media,
    onItemClick: (Media) -> Unit = {}
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(media)
                }
        ) {
            val (headerImage, playIcon, tag, title, caption) = createRefs()

            HeaderImage(media = media, reference = headerImage) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            if (media is Video) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = modifier
                        .size(60.dp)
                        .constrainAs(playIcon) {
                            start.linkTo(headerImage.start)
                            end.linkTo(headerImage.end)
                            bottom.linkTo(headerImage.bottom)
                            top.linkTo(headerImage.top)
                        }
                )
            }

            Tag(media = media, reference = tag) {
                start.linkTo(headerImage.start, margin = 16.dp)
                bottom.linkTo(headerImage.bottom)
                top.linkTo(headerImage.bottom)
            }
            BigTitle(media = media, reference = title) {
                start.linkTo(tag.start)
                top.linkTo(tag.bottom, margin = 8.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
            Caption(
                media = media,
                reference = caption,
                modifier = modifier.padding(bottom = 16.dp)
            ) {
                start.linkTo(tag.start)
                top.linkTo(title.bottom, margin = 4.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        }
    }
}

@Preview(showBackground = true, name = "nam")
@Composable
fun MediaCardPreview(
    @PreviewParameter(MediaPreviewParameterProvider::class) media: Media
) {
    MediaCard(media = media)
}
