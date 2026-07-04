package com.anoob.tvvideoscreensaver.player

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import android.util.Log

class PlaylistManager(

    private val context: Context,

    private val player: ExoPlayer

) {

    fun playPlaylist(videos: List<Uri>) {

        if (videos.isEmpty()) return

        player.stop()

        player.clearMediaItems()

        val mediaItems = videos.map {
            MediaItem.fromUri(it)
        }
        Log.d("PlaylistManager", "MediaItems = ${mediaItems.size}")

        player.setMediaItems(mediaItems, /* resetPosition = */ true)

        player.repeatMode = Player.REPEAT_MODE_ALL

        player.prepare()

        player.playWhenReady = true
    }

}