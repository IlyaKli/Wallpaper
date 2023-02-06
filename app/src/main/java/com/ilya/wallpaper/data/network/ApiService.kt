package com.ilya.wallpaper.data.network

import com.ilya.wallpaper.data.network.model.WallpaperResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?key=$API_KEY")
    suspend fun getWallpapers(
        @Query(QUERY_PARAM_CATEGORY) category: String = "",
        @Query(QUERY_PARAM_PAGE) page: Int,
    ): WallpaperResponseDto

    companion object {
        private const val API_KEY = "33106230-b104905cd7ff74ed17e2229af"
        private const val QUERY_PARAM_CATEGORY = "category"
        private const val QUERY_PARAM_PAGE = "page"
    }
}