package com.ilya.wallpaper.presentation.screen.wallpaper_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.wallpaper.domain.model.Wallpaper
import com.ilya.wallpaper.domain.usecase.LoadWallpaperUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WallpaperListViewModel @Inject constructor(
    private val loadWallpaperUseCase: LoadWallpaperUseCase
) : ViewModel() {

    private val _wallpapers = MutableLiveData<List<Wallpaper>>()
    val wallpapers: LiveData<List<Wallpaper>> = _wallpapers

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private var pageCount = 1
    private val wallpaperList = mutableListOf<Wallpaper>()

//    init {
//        loadWallpaper()
//    }

    fun loadWallpaper(categoryName: String) {
        _loading.value = true
        viewModelScope.launch {
            wallpaperList.addAll(loadWallpaperUseCase(categoryName, pageCount))
            _wallpapers.value = wallpaperList
            pageCount++
            _loading.value = false
        }
    }
}