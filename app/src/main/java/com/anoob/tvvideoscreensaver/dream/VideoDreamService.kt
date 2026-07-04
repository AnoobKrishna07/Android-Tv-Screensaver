package com.anoob.tvvideoscreensaver.dream

import android.service.dreams.DreamService
import androidx.media3.ui.PlayerView
import com.anoob.tvvideoscreensaver.R
import com.anoob.tvvideoscreensaver.player.VideoPlayerManager
import com.anoob.tvvideoscreensaver.repository.PlaylistRepository
import com.anoob.tvvideoscreensaver.repository.VideoRepository
import kotlinx.coroutines.*
import android.util.Log

class VideoDreamService : DreamService() {

    private lateinit var playerManager: VideoPlayerManager

    private val scope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        isInteractive = false
        isFullscreen = true

        setContentView(R.layout.activity_main)

        val playerView = findViewById<PlayerView>(R.id.playerView)

        playerManager = VideoPlayerManager(this, playerView)

        scope.launch {

            val playlistRepository =
                PlaylistRepository(this@VideoDreamService)

            val playlist =
                playlistRepository.getPlaylist()

            if (playlist.isNotEmpty()) {
                Log.d("DreamService", "Playlist size = ${playlist.size}")
                playerManager.playPlaylist(playlist)

            } else {

                val repository =
                    VideoRepository(this@VideoDreamService)

                playerManager.playPlaylist(

                    listOf(repository.getDefaultVideo())

                )
            }
        }
    }

    override fun onDreamingStopped() {
        super.onDreamingStopped()

        scope.cancel()

        playerManager.release()
    }
}