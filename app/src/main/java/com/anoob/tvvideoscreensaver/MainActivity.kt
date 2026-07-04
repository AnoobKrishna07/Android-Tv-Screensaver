package com.anoob.tvvideoscreensaver

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.media3.ui.PlayerView
import com.anoob.tvvideoscreensaver.player.VideoPlayerManager
import com.anoob.tvvideoscreensaver.repository.VideoRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var videoPlayerManager: VideoPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val playerView = findViewById<PlayerView>(R.id.playerView)

        videoPlayerManager = VideoPlayerManager(this, playerView)

        val intentUri = intent.getStringExtra("video_uri")

        lifecycleScope.launch {

            if (intentUri != null) {

                videoPlayerManager.playPlaylist(

                    listOf(Uri.parse(intentUri))

                )

            } else {

                val repository = VideoRepository(this@MainActivity)

                videoPlayerManager.playPlaylist(

                    listOf(repository.getDefaultVideo())

                )

            }
        }
    }

    override fun onStop() {
        super.onStop()
        videoPlayerManager.release()
    }
}