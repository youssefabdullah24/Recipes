package org.example.recipes.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL
import platform.UIKit.UIView


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(url: String, modifier: Modifier) {
    val player = remember { AVPlayer(uRL = NSURL.URLWithString(url)!!) }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }
    avPlayerViewController.player = player
    avPlayerViewController.showsPlaybackControls = true
    playerLayer.player = player

    UIKitView(
        factory = {
            val playerContainer = UIView()
            avPlayerViewController.view.setFrame(playerContainer.bounds)
            playerContainer.addSubview(avPlayerViewController.view)
            playerContainer
        },
        modifier = modifier,
        update = {
            player.play()
            avPlayerViewController.player?.play()
            avPlayerViewController.view.setFrame(it.bounds)
            playerLayer.setFrame(avPlayerViewController.view.bounds)
        },
        properties = UIKitInteropProperties(
            isInteractive = true,
            isNativeAccessibilityEnabled = true
        )
    )
}

