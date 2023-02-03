package com.ilya.wallpaper.presentation.adapter.wallpaper

import androidx.recyclerview.widget.DiffUtil
import com.ilya.wallpaper.domain.model.Wallpaper

class WallpaperDiffCallback : DiffUtil.ItemCallback<Wallpaper>() {
    override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
        return oldItem == newItem
    }
}