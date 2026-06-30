package com.anoob.tvvideoscreensaver.storage

import android.content.Context
import android.provider.MediaStore
import com.anoob.tvvideoscreensaver.models.VideoItem

class InternalStorageProvider(
    private val context: Context
) {

    fun getVideos(): List<VideoItem> {

        val videos = mutableListOf<VideoItem>()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION
        )

        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            MediaStore.Video.Media.DATE_ADDED + " DESC"
        )?.use { cursor ->

            val idIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)

            val nameIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)

            val durationIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)

            while (cursor.moveToNext()) {

                val id = cursor.getLong(idIndex)

                val name = cursor.getString(nameIndex)

                val duration = cursor.getLong(durationIndex)

                val contentUri =
                    android.content.ContentUris.withAppendedId(uri, id)

                videos.add(
                    VideoItem(
                        id,
                        name,
                        contentUri,
                        duration
                    )
                )
            }
        }

        return videos
    }
}