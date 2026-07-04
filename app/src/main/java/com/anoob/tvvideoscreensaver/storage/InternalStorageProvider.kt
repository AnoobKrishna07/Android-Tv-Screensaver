package com.anoob.tvvideoscreensaver.storage

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.anoob.tvvideoscreensaver.models.VideoItem

class InternalStorageProvider(
    private val context: Context
) {

    fun getVideos(): List<VideoItem> {

        val videos = mutableListOf<VideoItem>()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.HEIGHT
        )

        val sortOrder =
            "${MediaStore.Video.Media.DISPLAY_NAME} ASC"

        context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->

            Log.d("MediaStore", "Cursor Count = ${cursor.count}")

            val idColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)

            val nameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)

            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

            val widthColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)

            val heightColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)

            while (cursor.moveToNext()) {

                val id = cursor.getLong(idColumn)

                val name = cursor.getString(nameColumn)

                Log.d("MediaStore", "Found Video: $name")

                val uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                videos.add(
                    VideoItem(
                        name = name,
                        uri = uri,
                        duration = cursor.getLong(durationColumn),
                        size = cursor.getLong(sizeColumn),
                        width = cursor.getInt(widthColumn),
                        height = cursor.getInt(heightColumn)
                    )
                )
            }
        }

        Log.d("MediaStore", "Total Videos = ${videos.size}")

        return videos
    }
}