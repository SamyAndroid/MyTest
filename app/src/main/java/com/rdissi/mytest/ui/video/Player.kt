package com.rdissi.mytest.ui.video

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.rdissi.mytest.ui.error.ErrorScreen
import com.rdissi.mytest.ui.extension.hideSystemUI
import com.rdissi.mytest.ui.extension.requestPortraitMode
import com.rdissi.mytest.ui.extension.requestUnspecifiedMode
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView.SHOW_BUFFERING_ALWAYS

@Composable
fun Player(
    videoUrl: String,
) {
    if (videoUrl.isBlank()) {
        ErrorScreen(message = "url not available")
        return
    }
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    LaunchedEffect(key1 = Unit) {
        activity.hideSystemUI()
        activity.requestPortraitMode()
    }

    val exoPlayer = remember { context.buildExoPlayer(Uri.parse(videoUrl)) }
    val playerView = AndroidView(
        factory = { it.buildPlayerView(exoPlayer) },
        modifier = Modifier.fillMaxSize(),
    )
    DisposableEffect(
        key1 = playerView,
    ) {
        onDispose {
            exoPlayer.release()
            activity.requestUnspecifiedMode()
        }
    }
}

fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        setShowBuffering(SHOW_BUFFERING_ALWAYS)
        useController = true
    }

@Preview(showBackground = true)
@Composable
fun PlayerPreview() {
    Player(
        videoUrl = "www.google.fr",
    )
}
