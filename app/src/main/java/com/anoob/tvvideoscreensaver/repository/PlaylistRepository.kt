package com.anoob.tvvideoscreensaver.repository

import android.content.Context
import android.net.Uri
import com.anoob.tvvideoscreensaver.datastore.SettingsDataStore
import kotlinx.coroutines.flow.first

class PlaylistRepository(
    private val context: Context
) {

    suspend fun getPlaylist(): List<Uri> {

        return SettingsDataStore(context)
            .playlist
            .first()
            .map {
                Uri.parse(it)
            }

    }
}