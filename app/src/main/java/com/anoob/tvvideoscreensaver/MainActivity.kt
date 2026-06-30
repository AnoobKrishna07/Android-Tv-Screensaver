package com.anoob.tvvideoscreensaver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.ui.PlayerView
import com.anoob.tvvideoscreensaver.player.VideoPlayerManager
import com.anoob.tvvideoscreensaver.repository.VideoRepository

class MainActivity : AppCompatActivity() {

    private lateinit var videoPlayerManager: VideoPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val playerView = findViewById<PlayerView>(R.id.playerView)

        videoPlayerManager = VideoPlayerManager(this, playerView)

        val repository = VideoRepository(this)

        videoPlayerManager.play(repository.getVideoUri())
    }

    override fun onStop() {
        super.onStop()
        videoPlayerManager.release()
    }
}