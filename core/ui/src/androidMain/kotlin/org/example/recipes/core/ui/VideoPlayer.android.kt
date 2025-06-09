package org.example.recipes.core.ui

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.delay

@OptIn(UnstableApi::class)
@Composable
actual fun VideoPlayer(
    url: String,
    seekTo: Double,
    modifier: Modifier,
    onProgressChanged: (Long) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val item = MediaItem.fromUri(url)
            setMediaItem(item)
            playWhenReady = true
            prepare()
        }
    }
    LaunchedEffect(exoPlayer) {
        while (true) {
            onProgressChanged(exoPlayer.currentPosition)
            delay(1000L)
        }

    }
    Box(modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                PlayerView(context).apply {
                    setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    player = exoPlayer
                    /* exoPlayer.addListener(object : Player.Listener {
                         override fun onPositionDiscontinuity(
                             oldPosition: Player.PositionInfo,
                             newPosition: Player.PositionInfo,
                             reason: Int
                         ) {
                             super.onPositionDiscontinuity(oldPosition, newPosition, reason)
                             Logger.d("PROGRESS INSIDE ${newPosition.positionMs}" )

                             if (oldPosition.positionMs != newPosition.positionMs)
                                 onProgressChanged(newPosition.positionMs)
                         }
                     })*/

                }
            },
            update = {
                it.player?.seekTo(seekTo.toLong())
            }
        )
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> {
                    exoPlayer.pause()
                }

                Lifecycle.Event.ON_DESTROY -> {
                    exoPlayer.release()
                }

                else -> {}
            }

        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
