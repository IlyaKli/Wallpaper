package com.ilya.wallpaper.presentation.screen.wallpaper_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.wallpaper.data.repository.WallpaperRepositoryImpl
import com.ilya.wallpaper.domain.usecase.LoadWallpaperUseCase
import androidx.lifecycle.viewModelScope
import com.ilya.wallpaper.domain.model.Wallpaper
import kotlinx.coroutines.launch

class WallpaperListViewModel(private val categoryName: String) : ViewModel() {

    val repository = WallpaperRepositoryImpl()

    private val loadWallpaperUseCase = LoadWallpaperUseCase(repository)

    private val _wallpapers = MutableLiveData<List<Wallpaper>>()
    val wallpapers: LiveData<List<Wallpaper>> = _wallpapers

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private var pageCount = 1
    private val wallpaperList = mutableListOf<Wallpaper>()

    init {
        loadWallpaper()
    }

    fun loadWallpaper() {
        _loading.value = true
        viewModelScope.launch {
            wallpaperList.addAll(loadWallpaperUseCase(categoryName, pageCount))
            _wallpapers.value = wallpaperList
            pageCount++
            _loading.value = false
        }
    }
}