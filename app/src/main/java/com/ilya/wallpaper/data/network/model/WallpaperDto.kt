package com.ilya.wallpaper.data.network.model

import com.google.gson.annotations.SerializedName

class WallpaperDto (
    @SerializedName("id")
    val id: Int,

    @SerializedName("webformatURL")
    val webImageURL: String,

    @SerializedName("largeImageURL")
    val largeImageURL: String
)