package com.anoob.tvvideoscreensaver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.anoob.tvvideoscreensaver.R
import com.anoob.tvvideoscreensaver.models.VideoItem

class VideoAdapter(

    private val borderColors: List<Int> = listOf(
        0xFF2196F3.toInt(),
        0xFF9C27B0.toInt(),
        0xFFFF9800.toInt(),
        0xFF4CAF50.toInt(),
        0xFFE91E63.toInt(),
        0xFF00BCD4.toInt()
    ),

    private val videos: List<VideoItem>,

    private val onPreview: (VideoItem) -> Unit,

    private val onSelectionChanged: (Int) -> Unit

) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val card: MaterialCardView =
            view.findViewById(R.id.cardVideo)

        val thumbnail: ImageView =
            view.findViewById(R.id.imgThumbnail)

        val name: TextView =
            view.findViewById(R.id.txtVideoName)

        val format: TextView =
            view.findViewById(R.id.txtFormat)

        val resolution: TextView =
            view.findViewById(R.id.txtResolution)

        val size: TextView =
            view.findViewById(R.id.txtSize)

        val duration: TextView =
            view.findViewById(R.id.txtDuration)

        val preview: Button =
            view.findViewById(R.id.btnPreview)

        val set: Button =
            view.findViewById(R.id.btnSetScreenSaver)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)

        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(
        holder: VideoViewHolder,
        position: Int
    ) {

        val video = videos[position]

        // Card border color
        holder.card.strokeColor =
            borderColors[position % borderColors.size]

        // Video title
        holder.name.text = video.name

        // Video info
        val extension =
            video.name.substringAfterLast(".", "Unknown").uppercase()

        val seconds = video.duration / 1000
        val minutes = seconds / 60
        val remain = seconds % 60

        val sizeMB =
            video.size / 1024f / 1024f

        holder.format.text = extension
        holder.resolution.text =
            "${video.width} × ${video.height}"
        holder.size.text =
            String.format("%.1f MB", sizeMB)
        holder.duration.text =
            String.format("%02d:%02d", minutes, remain)

        // Thumbnail
        Glide.with(holder.itemView.context)
            .load(video.uri)
            .centerCrop()
            .override(320, 180)
            .dontAnimate()
            .into(holder.thumbnail)

        // Preview button
        holder.preview.setBackgroundResource(
            R.drawable.button_preview
        )

        holder.preview.setOnClickListener {
            onPreview(video)
        }

        // Selection button
        if (video.selected) {

            holder.set.text = "✔ Added"

            holder.set.setBackgroundResource(
                R.drawable.button_current
            )

        } else {

            holder.set.text = "+ Add"

            holder.set.setBackgroundResource(
                R.drawable.button_outline
            )
        }

        holder.set.setOnClickListener {

            video.selected = !video.selected

            notifyItemChanged(position)

            onSelectionChanged(
                videos.count { it.selected }
            )
        }

        // Focus animations
        setupButtonFocus(
            holder.preview,
            holder.card
        )

        setupButtonFocus(
            holder.set,
            holder.card
        )
    }

    private fun setupButtonFocus(
        button: Button,
        card: MaterialCardView
    ) {

        button.setOnFocusChangeListener { view, hasFocus ->

            if (hasFocus) {

                card.strokeWidth = 5
                card.cardElevation = 18f

                view.animate()
                    .scaleX(1.08f)
                    .scaleY(1.08f)
                    .setDuration(150)
                    .start()

            } else {

                card.strokeWidth = 2
                card.cardElevation = 8f

                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(150)
                    .start()
            }
        }
    }

    /**
     * Returns all selected videos.
     */
    fun getSelectedVideos(): List<VideoItem> {

        return videos.filter { it.selected }

    }
}