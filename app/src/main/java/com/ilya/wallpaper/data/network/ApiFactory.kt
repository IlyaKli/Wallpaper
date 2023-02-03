package com.ilya.wallpaper.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

        private const val BASE_URL = "https://pixabay.com/api/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpLoggingInterceptor())
            .build()

        val apiService = retrofit.create(ApiService::class.java) ?: throw RuntimeException("Api service = null in retrofit create")

        private fun httpLoggingInterceptor(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
}