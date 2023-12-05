package com.rdissi.mytest.ui.story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.rdissi.mytest.common.DateFormatter
import com.rdissi.mytest.common.capitalized
import com.rdissi.mytest.domain.model.Sport
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.ui.component.HeaderImage
import com.rdissi.mytest.ui.theme.Blue

@Composable
fun StoryPage(
    story: Story
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        val (headerImage, tagLabel, title, caption, underCaption, teaser) = createRefs()

        HeaderImage(media = story, reference = headerImage) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        Text(
            text = story.sport.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.small)
                .background(Blue)
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .constrainAs(tagLabel) {
                    start.linkTo(headerImage.start, margin = 16.dp)
                    bottom.linkTo(headerImage.bottom)
                    top.linkTo(headerImage.bottom)
                },
        )
        Text(
            text = story.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(tagLabel.start)
                top.linkTo(tagLabel.bottom, margin = 8.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                ) {
                    append("By")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color(red = 65, green = 160, blue = 253)
                    )
                ) {
                    append(" ${story.author.capitalized()}")
                }

            },
            modifier = Modifier.constrainAs(caption) {
                start.linkTo(tagLabel.start)
                top.linkTo(title.bottom, margin = 4.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
        )
        Text(
            text = DateFormatter.formatLastViewTime(story.date.toLong()),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.LightGray,
            modifier = Modifier.constrainAs(underCaption) {
                start.linkTo(tagLabel.start)
                top.linkTo(caption.bottom)
                end.linkTo(headerImage.end)
                width = Dimension.fillToConstraints
            },
        )

        Text(
            text = story.teaser,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.constrainAs(teaser) {
                start.linkTo(tagLabel.start)
                top.linkTo(underCaption.bottom, margin = 16.dp)
                end.linkTo(headerImage.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StoryPagePreview() {
    StoryPage(
        story = Story(
            id = 5842,
            title = "magnis",
            date = 6.7,
            sport = Sport(id = 6503, name = "Fran Rodriquez"),
            author = "dictum",
            image = "solet",
            teaser = "Ce soir, c'est le weekend ! En ce 46e jour de confinement, il y a donc toutes les raisons d'avoir la forme. Surtout qu'il va y avoir de l'actualité ce jeudi. Cerise sur le gâteau : si vous voulez vous replonger dans les plus belles heures du sport, on a tout ce qu’il faut pour vous. Voici notre plateau matinal.",
        ),
    )
}