package com.anoob.tvvideoscreensaver.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class SettingsDataStore(private val context: Context) {

    companion object {

        val VIDEO_SOURCE =
            stringPreferencesKey("video_source")

        val LOOP =
            booleanPreferencesKey("loop")

        val NETWORK_URL =
            stringPreferencesKey("network_url")
    }

    suspend fun saveVideoSource(source: String) {

        context.dataStore.edit {

            it[VIDEO_SOURCE] = source

        }

    }

    suspend fun saveLoop(loop: Boolean) {

        context.dataStore.edit {

            it[LOOP] = loop

        }

    }

    suspend fun saveNetworkUrl(url: String) {

        context.dataStore.edit {

            it[NETWORK_URL] = url

        }

    }

    val videoSource: Flow<String> =

        context.dataStore.data.map {

            it[VIDEO_SOURCE] ?: "BUILTIN"

        }

    val loop: Flow<Boolean> =

        context.dataStore.data.map {

            it[LOOP] ?: true

        }

    val networkUrl: Flow<String> =

        context.dataStore.data.map {

            it[NETWORK_URL] ?: ""

        }

}