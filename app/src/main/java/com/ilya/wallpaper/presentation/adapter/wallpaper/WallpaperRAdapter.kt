package com.ilya.wallpaper.presentation.adapter.wallpaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.ilya.wallpaper.databinding.WallpaperItemBinding
import com.ilya.wallpaper.domain.model.Wallpaper

class WallpaperRAdapter : ListAdapter<Wallpaper, WallpaperViewHolder>(WallpaperDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null

    var wallpaperClickListener: ((wallpaper: Wallpaper) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val binding = WallpaperItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WallpaperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val wallpaper = getItem(position)

        with(holder.binding) {
            Glide.with(wallpaperImageView)
                .load(wallpaper.webImageURL)
                .centerCrop()
                .into(wallpaperImageView)
            wallpaperCardView.setOnClickListener {
                wallpaperClickListener?.invoke(wallpaper)
            }
        }
        if (position == currentList.lastIndex - 6) {
            onReachEndListener?.invoke()
        }
    }
}