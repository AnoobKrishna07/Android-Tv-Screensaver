package com.anoob.tvvideoscreensaver.models

import android.net.Uri

data class VideoItem(
    val id: Long,
    val name: String,
    val uri: Uri,
    val duration: Long
)