package org.example.recipes.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVFoundation.seekToTime
import platform.AVKit.AVPlayerViewController
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.NSURL
import platform.UIKit.UIEdgeInsetsMake
import platform.UIKit.UIView
import platform.UIKit.setAdditionalSafeAreaInsets


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(
    url: String,
    seekTo: Double,
    modifier: Modifier,
    onClick: (Int) -> Unit
) {
    val player = remember { AVPlayer(uRL = NSURL.URLWithString(url)!!) }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }
    avPlayerViewController.player = player
    avPlayerViewController.setAdditionalSafeAreaInsets(UIEdgeInsetsMake(52.0, 50.0, 0.0, 0.0))
    avPlayerViewController.showsPlaybackControls = true
    player.seekToTime(CMTimeMakeWithSeconds(seekTo / 1000, 1000))
    playerLayer.player = player

    UIKitView(
        modifier = modifier,
        factory = {
            val playerContainer = UIView()
            avPlayerViewController.view.setFrame(playerContainer.bounds)
            playerContainer.addSubview(avPlayerViewController.view)
            playerContainer
        },
        update = {
            player.play()
            avPlayerViewController.player?.play()
            avPlayerViewController.view.setFrame(it.bounds)
            playerLayer.setFrame(avPlayerViewController.view.bounds)
        },
        onRelease = {
            player.pause()
            playerLayer.removeFromSuperlayer()
            avPlayerViewController.player = null
            player.replaceCurrentItemWithPlayerItem(null)

        },
        properties = UIKitInteropProperties(
            isInteractive = true,
            isNativeAccessibilityEnabled = true
        )
    )
}

