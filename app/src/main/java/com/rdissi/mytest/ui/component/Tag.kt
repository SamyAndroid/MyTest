package com.rdissi.mytest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.rdissi.mytest.data.local.mock.MockDataSource
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video
import com.rdissi.mytest.ui.theme.Blue

@Composable
fun ConstraintLayoutScope.Tag(
    modifier: Modifier = Modifier,
    media: Media,
    reference: ConstrainedLayoutReference,
    constraint: ConstrainScope.() -> Unit
) {
    Text(
        text = when (media) {
            is Story -> media.sport.name
            is Video -> media.sport.name
            else -> "Sport"
        },
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.small)
            .background(Blue)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .constrainAs(reference, constraint)
    )
}

@Preview(showBackground = true)
@Composable
fun TagPreview() {
    ConstraintLayout{
        val ref = createRef()
        Tag(
            media = MockDataSource.getFirstMockVideo(),
            reference = ref
        ) {

        }
    }
}