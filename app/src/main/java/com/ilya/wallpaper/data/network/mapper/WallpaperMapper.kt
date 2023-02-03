package com.ilya.wallpaper.data.network.mapper

import com.ilya.wallpaper.data.network.model.WallpaperDto
import com.ilya.wallpaper.domain.model.Wallpaper

class WallpaperMapper {

    fun mapListDtoToEntity(dto: List<WallpaperDto>) =
        dto.map {
            mapDtoToEntity(it)
        }

    private fun mapDtoToEntity(dto: WallpaperDto) = Wallpaper(
        id = dto.id,
        webImageURL = dto.webImageURL,
        largeImageURL = dto.largeImageURL
    )
}