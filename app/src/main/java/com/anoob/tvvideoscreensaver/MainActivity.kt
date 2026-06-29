package com.anoob.tvvideoscreensaver

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.media3.ui.PlayerView
import com.anoob.tvvideoscreensaver.player.VideoPlayerManager
import com.anoob.tvvideoscreensaver.repository.VideoRepository

class MainActivity : FragmentActivity() {

    private lateinit var videoPlayerManager: VideoPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Get the PlayerView from the layout
        val playerView = findViewById<PlayerView>(R.id.playerView)

        // Initialize VideoPlayerManager
        videoPlayerManager = VideoPlayerManager(this, playerView)

        // Play the bundled sample video
        val repository = VideoRepository(this)

        videoPlayerManager.play(repository.getVideoUri())
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()

        // Release ExoPlayer resources
        videoPlayerManager.release()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}