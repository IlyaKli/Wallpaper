package com.ilya.wallpaper.data.network.model

import com.google.gson.annotations.SerializedName

class WallpaperResponseDto (
    @SerializedName("hits")
    val wallpapers: List<WallpaperDto>
    )