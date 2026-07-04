package com.anoob.tvvideoscreensaver.models

import android.net.Uri

data class VideoItem(

    val uri: Uri,

    val name: String,

    val duration: Long,

    val size: Long,

    val width: Int,

    val height: Int,

    var selected: Boolean = false

)