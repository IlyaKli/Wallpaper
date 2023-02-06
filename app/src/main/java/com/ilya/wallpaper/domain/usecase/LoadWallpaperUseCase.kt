package com.ilya.wallpaper.domain.usecase

import com.ilya.wallpaper.domain.repository.WallpaperRepository

class LoadWallpaperUseCase (val repository: WallpaperRepository) {

    suspend operator fun invoke(categoryName: String, pageCount: Int) = repository
        .loadWallpaperUseCase(categoryName, pageCount)
}