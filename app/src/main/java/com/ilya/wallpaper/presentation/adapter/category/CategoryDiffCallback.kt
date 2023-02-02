package com.ilya.wallpaper.presentation.adapter.category

import androidx.recyclerview.widget.DiffUtil
import com.ilya.wallpaper.domain.model.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}