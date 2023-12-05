package com.rdissi.mytest.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rdissi.mytest.R
import com.rdissi.mytest.data.local.mock.MockDataSource
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video

@Composable
fun ConstraintLayoutScope.HeaderImage(
    modifier: Modifier = Modifier,
    media: Media,
    reference: ConstrainedLayoutReference,
    constraint: ConstrainScope.() -> Unit
) {
    val context = LocalContext.current
    val ratio by remember { mutableFloatStateOf(16F / 9) }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(
                when (media) {
                    is Story -> media.image
                    is Video -> media.thumb
                    else -> ""
                }
            )
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(ratio)
            .constrainAs(reference, constraint),
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderImagePreview() {
    ConstraintLayout {
        val ref = createRef()
        HeaderImage(
            media = MockDataSource.getFirstMockStory(),
            reference = ref,
        ) {}
    }
}
