package com.rdissi.mytest.ui.story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rdissi.mytest.ui.component.SideEffectWithLifeCycle
import com.rdissi.mytest.ui.component.showToast
import com.rdissi.mytest.ui.error.ErrorScreen
import com.rdissi.mytest.ui.story.StoryViewModel.UiState
import com.rdissi.mytest.ui.toolkit.StoryResultPreviewParameterProvider

@Composable
fun StoryScreen(
    modifier: Modifier = Modifier,
    storyId: Int,
    storyViewModel: StoryViewModel = hiltViewModel()
) {

    val uiState by storyViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = storyViewModel) {
        storyViewModel.uiEvent.collect { event ->
            when (event) {
                is StoryViewModel.UiEvent.Toast -> showToast(context, event.message)
            }
        }
    }
    SideEffectWithLifeCycle(
        lifecycleOwner = lifecycleOwner,
        onStart = { storyViewModel.getStoryById(storyId) }
    )
    StoryResult(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun StoryResult(
    modifier: Modifier = Modifier,
    uiState: UiState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (uiState) {
            UiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is UiState.Success -> {
                StoryPage(story = uiState.story)
            }
            is UiState.Error -> {
                ErrorScreen(message = "No Story found")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StoryScreenPreview(
    @PreviewParameter(StoryResultPreviewParameterProvider::class) uiState: UiState
) {
    StoryResult(uiState = uiState)
}