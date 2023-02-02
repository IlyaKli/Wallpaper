package com.ilya.wallpaper.presentation.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ilya.wallpaper.databinding.CategoryItemBinding

//class CategoryRAdapter : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {
//
//    var onReachEndListener: (() -> Unit)? = null
//
//    var categoryClickListener: ((category: Category) -> Unit)? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
//        val binding = CategoryItemBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return CategoryViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
//        val category = getItem(position)
//
//        holder.binding.categoryItemTextView.text = movie.rating.kp.toString()
//
//        if (position >= currentList.lastIndex - 10) {
//            onReachEndListener?.invoke()
//        }
//    }
//}