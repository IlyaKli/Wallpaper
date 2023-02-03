package com.ilya.wallpaper.presentation.screen.wallpaper_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.wallpaper.data.repository.WallpaperRepositoryImpl
import com.ilya.wallpaper.domain.usecase.LoadWallpaperUseCase
import androidx.lifecycle.viewModelScope
import com.ilya.wallpaper.domain.model.Wallpaper
import kotlinx.coroutines.launch

class WallpaperListViewModel : ViewModel() {

    val repository = WallpaperRepositoryImpl()

    private val loadWallpaperUseCase = LoadWallpaperUseCase(repository)

    private val _wallpapers = MutableLiveData<List<Wallpaper>>()
    val wallpapers: LiveData<List<Wallpaper>> = _wallpapers

    fun loadWallpaper(name: String) {
        viewModelScope.launch {
            _wallpapers.value = loadWallpaperUseCase(name)
        }
    }
}