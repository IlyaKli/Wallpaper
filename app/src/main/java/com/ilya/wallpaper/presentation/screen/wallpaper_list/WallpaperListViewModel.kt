package com.ilya.wallpaper.presentation.screen.wallpaper_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.wallpaper.domain.model.Wallpaper
import com.ilya.wallpaper.domain.usecase.LoadWallpaperUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class WallpaperListViewModel @Inject constructor(
    private val loadWallpaperUseCase: LoadWallpaperUseCase,
    private val categoryName: String
) : ViewModel() {

    private val _wallpapers = MutableLiveData<List<Wallpaper>>()
    val wallpapers: LiveData<List<Wallpaper>> = _wallpapers

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private var pageCount = 1
    private val wallpaperList = mutableListOf<Wallpaper>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _loading.value = false
        if (throwable.message.toString() == INTERNET_DISCONNECTED_ERROR) {
            _error.value = INTERNET_DISCONNECTED_TOAST
        } else {
            _error.value = throwable.message
        }
        _error.value = null
    }

    init {
        loadWallpaper()
    }

    fun loadWallpaper() {
        _loading.value = true
        viewModelScope.launch(exceptionHandler) {
            wallpaperList.addAll(loadWallpaperUseCase(categoryName, pageCount))
            _wallpapers.value = wallpaperList
            pageCount++
            _loading.value = false
        }
    }

    companion object {
        private const val INTERNET_DISCONNECTED_ERROR = """Unable to resolve host "pixabay.com": No address associated with hostname"""
        private const val INTERNET_DISCONNECTED_TOAST = "Отсутствует интернет соединение"
    }
}