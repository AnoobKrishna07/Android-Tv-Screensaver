package com.anoob.tvvideoscreensaver.player

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.anoob.tvvideoscreensaver.datastore.SettingsDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancel
import android.net.Uri

class VideoPlayerManager(
    context: Context,
    playerView: PlayerView
) {

    private val player = ExoPlayer.Builder(context).build()

    private val playlistManager =
        PlaylistManager(context, player)

    private val scope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    init {

        playerView.player = player

        player.repeatMode = Player.REPEAT_MODE_ALL

        player.playWhenReady = true

        playerView.useController = false

        playerView.resizeMode =
            AspectRatioFrameLayout.RESIZE_MODE_ZOOM

        playerView.keepScreenOn = true

        // Observe mute setting
        scope.launch {

            SettingsDataStore(context)
                .mute
                .collectLatest { muted ->

                    player.volume =
                        if (muted) 0f else 1f

                }
        }
    }


    fun playPlaylist(list: List<Uri>) {

        playlistManager.playPlaylist(list)

    }

    fun release() {

        scope.cancel()

        player.release()

    }
}