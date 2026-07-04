package com.anoob.tvvideoscreensaver.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anoob.tvvideoscreensaver.datastore.SettingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) :
    AndroidViewModel(application) {

    private val dataStore = SettingsDataStore(application)

    val loop: Flow<Boolean> = dataStore.loop

    val mute: Flow<Boolean> = dataStore.mute



    fun saveLoop(enabled: Boolean) {
        viewModelScope.launch {
            dataStore.saveLoop(enabled)
        }
    }

    fun saveMute(enabled: Boolean) {
        viewModelScope.launch {
            dataStore.saveMute(enabled)
        }
    }
}