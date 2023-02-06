package com.ilya.wallpaper.domain.repository

import com.ilya.wallpaper.domain.model.Wallpaper

interface WallpaperRepository {

    suspend fun loadWallpaperUseCase(categoryName: String, pageCount: Int): List<Wallpaper>
}