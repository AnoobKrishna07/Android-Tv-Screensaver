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


        val PLAYLIST =
            stringPreferencesKey("playlist")

        val LOOP =
            booleanPreferencesKey("loop")

        val MUTE =
            booleanPreferencesKey("mute")

    }




    suspend fun savePlaylist(list: List<String>) {

        context.dataStore.edit {

            it[PLAYLIST] =
                list.joinToString("|")

        }

    }

    suspend fun saveLoop(loop: Boolean) {
        context.dataStore.edit {
            it[LOOP] = loop
        }
    }
    suspend fun saveMute(enabled: Boolean) {

        context.dataStore.edit {

            it[MUTE] = enabled

        }
    }

    val loop: Flow<Boolean> =
        context.dataStore.data.map {
            it[LOOP] ?: true
        }
    val mute: Flow<Boolean> =
        context.dataStore.data.map {

            it[MUTE] ?: false

        }
    val playlist: Flow<List<String>> =

        context.dataStore.data.map {

            val data = it[PLAYLIST] ?: ""

            if (data.isEmpty()) {

                emptyList()

            } else {

                data.split("|")

            }

        }
}