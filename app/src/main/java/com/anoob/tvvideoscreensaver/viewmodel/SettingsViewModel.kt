package com.anoob.tvvideoscreensaver.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anoob.tvvideoscreensaver.datastore.SettingsDataStore
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) :
    AndroidViewModel(application) {

    private val dataStore = SettingsDataStore(application)

    val videoSource = dataStore.videoSource
    val loop = dataStore.loop
    val networkUrl = dataStore.networkUrl

    fun saveVideoSource(source: String) {
        viewModelScope.launch {
            dataStore.saveVideoSource(source)
        }
    }

    fun saveLoop(enabled: Boolean) {
        viewModelScope.launch {
            dataStore.saveLoop(enabled)
        }
    }

    fun saveNetworkUrl(url: String) {
        viewModelScope.launch {
            dataStore.saveNetworkUrl(url)
        }
    }
}