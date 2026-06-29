package com.anoob.tvvideoscreensaver.player

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class VideoPlayerManager(
    private val context: Context,
    private val playerView: PlayerView
) {

    private val player = ExoPlayer.Builder(context).build()

    init {
        playerView.player = player
        playerView.useController = false
    }

    fun play(uri: Uri) {
        player.setMediaItem(MediaItem.fromUri(uri))
        player.repeatMode = Player.REPEAT_MODE_ONE
        player.prepare()
        player.play()
    }

    fun release() {
        player.release()
    }
}