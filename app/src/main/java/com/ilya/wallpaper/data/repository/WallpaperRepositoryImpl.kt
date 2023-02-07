package com.ilya.wallpaper.data.repository

import com.ilya.wallpaper.data.network.ApiService
import com.ilya.wallpaper.data.mapper.WallpaperMapper
import com.ilya.wallpaper.domain.model.Wallpaper
import com.ilya.wallpaper.domain.repository.WallpaperRepository
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private val mapper: WallpaperMapper,
    private val apiService: ApiService
): WallpaperRepository {

    override suspend fun loadWallpaperUseCase(categoryName: String, pageCount: Int): List<Wallpaper> {
        return mapper.mapListDtoToEntity(apiService.getWallpapers(categoryName, pageCount).wallpapers)
    }
}