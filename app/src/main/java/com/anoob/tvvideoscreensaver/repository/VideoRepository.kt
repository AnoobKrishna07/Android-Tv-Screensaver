package com.anoob.tvvideoscreensaver.repository

import android.content.Context
import android.net.Uri
import com.anoob.tvvideoscreensaver.R

class VideoRepository(private val context: Context) {

    fun getVideoUri(): Uri {
        return Uri.parse(
            "android.resource://${context.packageName}/${R.raw.sample}"
        )
    }
}