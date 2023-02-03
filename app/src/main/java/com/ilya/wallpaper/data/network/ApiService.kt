package com.ilya.wallpaper.data.network

import com.ilya.wallpaper.data.network.model.WallpaperDto
import com.ilya.wallpaper.data.network.model.WallpaperResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?key=33106230-b104905cd7ff74ed17e2229af")
    suspend fun getWallpapers(
//        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
//        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): WallpaperResponseDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}