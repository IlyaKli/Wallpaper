package com.ilya.wallpaper.presentation.view_model_factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilya.wallpaper.presentation.screen.wallpaper_list.WallpaperListViewModel

class WallpaperListViewModelFactory (private val categoryName: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WallpaperListViewModel::class.java)) {
            return WallpaperListViewModel(categoryName) as T
        } else {
            throw RuntimeException("Unknown view model class $modelClass")
        }
    }
}