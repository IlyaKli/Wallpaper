package com.ilya.wallpaper.data.repository

import com.ilya.wallpaper.data.network.ApiFactory
import com.ilya.wallpaper.data.network.mapper.WallpaperMapper
import com.ilya.wallpaper.domain.model.Wallpaper
import com.ilya.wallpaper.domain.repository.WallpaperRepository

class WallpaperRepositoryImpl: WallpaperRepository {

    val apiService = ApiFactory.apiService
    val mapper = WallpaperMapper()

    override suspend fun loadWallpaperUseCase(name: String): List<Wallpaper> {
        return mapper.mapListDtoToEntity(apiService.getWallpapers().wallpapers)
    }
}