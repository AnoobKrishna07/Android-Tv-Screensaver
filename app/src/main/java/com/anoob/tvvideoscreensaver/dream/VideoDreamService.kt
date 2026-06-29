package com.anoob.tvvideoscreensaver.dream

import android.net.Uri
import android.service.dreams.DreamService
import androidx.media3.ui.PlayerView
import com.anoob.tvvideoscreensaver.R
import com.anoob.tvvideoscreensaver.player.VideoPlayerManager

class VideoDreamService : DreamService() {

    private lateinit var videoPlayerManager: VideoPlayerManager

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        isFullscreen = true
        isInteractive = false

        setContentView(R.layout.activity_main)

        val playerView = findViewById<PlayerView>(R.id.playerView)

        videoPlayerManager = VideoPlayerManager(this, playerView)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.sample}")

        videoPlayerManager.play(uri)
    }

    override fun onDreamingStopped() {
        super.onDreamingStopped()

        videoPlayerManager.release()
    }
}