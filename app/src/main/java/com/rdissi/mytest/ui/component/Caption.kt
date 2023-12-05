package com.rdissi.mytest.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.rdissi.mytest.common.DateFormatter
import com.rdissi.mytest.data.local.mock.MockDataSource
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video

@Composable
fun ConstraintLayoutScope.Caption(
    modifier: Modifier = Modifier,
    media: Media,
    reference: ConstrainedLayoutReference,
    constraint: ConstrainScope.() -> Unit
) {
    Text(
        text = when (media) {
            is Story -> "${media.author} - ${DateFormatter.formatLastViewTime(media.date.toLong())}"
            is Video -> "${media.views} views"
            else -> ""
        },
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Color.LightGray,
        modifier = modifier.constrainAs(reference, constraint)
    )
}

@Preview(showBackground = true)
@Composable
fun CaptionPreview() {
    ConstraintLayout{
        val ref = createRef()
        Caption(
            media = MockDataSource.getFirstMockStory(),
            reference = ref
        ) {}
    }
}