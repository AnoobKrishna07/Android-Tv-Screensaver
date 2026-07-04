package com.anoob.tvvideoscreensaver.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anoob.tvvideoscreensaver.MainActivity
import com.anoob.tvvideoscreensaver.R
import com.anoob.tvvideoscreensaver.adapter.VideoAdapter
import com.anoob.tvvideoscreensaver.models.VideoItem
import com.anoob.tvvideoscreensaver.storage.InternalStorageProvider
import androidx.lifecycle.lifecycleScope
import com.anoob.tvvideoscreensaver.datastore.SettingsDataStore
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class VideoLibraryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var txtVideoCount: TextView

    private lateinit var btnRefresh: Button

    private lateinit var btnSavePlaylist: Button

    private lateinit var videos: MutableList<VideoItem>

    companion object {

        private const val REQUEST_VIDEO_PERMISSION = 100

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video_library)

        recyclerView = findViewById(R.id.recyclerVideos)
        txtVideoCount = findViewById(R.id.txtVideoCount)

        btnRefresh = findViewById(R.id.btnRefresh)
        btnSavePlaylist = findViewById(R.id.btnSavePlaylist)

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnRefresh.setOnClickListener {

            Toast.makeText(
                this,
                "Refreshing Library...",
                Toast.LENGTH_SHORT
            ).show()

            loadVideos()
        }

        btnSavePlaylist.setOnClickListener {

            savePlaylist()

        }

        setupButtonFocus(btnRefresh)
        setupButtonFocus(btnSavePlaylist)

        checkPermission()
    }

    private fun checkPermission() {

        val permission =
            if (Build.VERSION.SDK_INT >= 33)
                Manifest.permission.READ_MEDIA_VIDEO
            else
                Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            loadVideos()

        } else {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                REQUEST_VIDEO_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )

        if (requestCode == REQUEST_VIDEO_PERMISSION &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {

            loadVideos()

        }
    }

    private fun loadVideos() {

        val provider = InternalStorageProvider(this)

        videos = provider.getVideos().toMutableList()

        lifecycleScope.launch {

            val savedPlaylist =
                SettingsDataStore(this@VideoLibraryActivity)
                    .playlist
                    .first()

            videos.forEach {

                it.selected =
                    savedPlaylist.contains(it.uri.toString())

            }

            txtVideoCount.text =
                "${videos.size} Videos • ${videos.count { it.selected }} Selected"

            recyclerView.adapter = VideoAdapter(

                videos = videos,

                onPreview = { selectedVideo ->

                    val intent = Intent(
                        this@VideoLibraryActivity,
                        MainActivity::class.java
                    )

                    intent.putExtra(
                        "video_uri",
                        selectedVideo.uri.toString()
                    )

                    startActivity(intent)

                },

                onSelectionChanged = { selectedCount ->

                    txtVideoCount.text =
                        "${videos.size} Videos • $selectedCount Selected"

                }

            )

        }
    }

    private fun savePlaylist() {

        val playlist = videos
            .filter { it.selected }
            .map { it.uri.toString() }

        if (playlist.isEmpty()) {

            Toast.makeText(
                this,
                "Select at least one video.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        lifecycleScope.launch {

            val store =
                SettingsDataStore(this@VideoLibraryActivity)

            store.savePlaylist(playlist)

            val saved =
                store.playlist.first()

            Toast.makeText(
                this@VideoLibraryActivity,
                "Saved: ${saved.size} videos",
                Toast.LENGTH_LONG
            ).show()
            Toast.makeText(
                this@VideoLibraryActivity,
                "Playlist saved (${playlist.size} videos).",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun setupButtonFocus(button: Button) {

        button.setOnFocusChangeListener { view, hasFocus ->

            if (hasFocus) {

                view.animate()
                    .scaleX(1.1f)
                    .scaleY(1.1f)
                    .setDuration(150)
                    .start()

            } else {

                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(150)
                    .start()
            }
        }
    }
}