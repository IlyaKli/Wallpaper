package com.ilya.wallpaper.domain.usecase

import com.ilya.wallpaper.domain.repository.WallpaperRepository

class LoadWallpaperUseCase (val repository: WallpaperRepository) {

    suspend operator fun invoke(name: String) = repository.loadWallpaperUseCase(name)
}