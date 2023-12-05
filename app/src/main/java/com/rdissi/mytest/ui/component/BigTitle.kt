package com.rdissi.mytest.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.rdissi.mytest.data.local.mock.MockDataSource
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video

@Composable
fun ConstraintLayoutScope.BigTitle(
    modifier: Modifier = Modifier,
    media: Media,
    reference: ConstrainedLayoutReference,
    constraint: ConstrainScope.() -> Unit
) {
    Text(
        text = when (media) {
            is Story -> media.title
            is Video -> media.title
            else -> ""
        },
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.constrainAs(reference, constraint)
    )
}

@Preview(showBackground = true)
@Composable
fun BigTitlePreview() {
    ConstraintLayout{
        val ref = createRef()
        BigTitle(
            media = MockDataSource.getFirstMockStory(),
            reference = ref
        ) {}
    }
}