package com.ilya.wallpaper.data.network

import com.ilya.wallpaper.data.network.model.WallpaperResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?key=33106230-b104905cd7ff74ed17e2229af")
    suspend fun getWallpapers(
        @Query(QUERY_PARAM_CATEGORY) category: String = ""
    ): WallpaperResponseDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_CATEGORY	 = "category"
    }
}