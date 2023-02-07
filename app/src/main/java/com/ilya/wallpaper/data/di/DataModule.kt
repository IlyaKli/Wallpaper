package com.ilya.wallpaper.data.di


import com.ilya.wallpaper.data.network.ApiContact
import com.ilya.wallpaper.data.network.ApiService
import com.ilya.wallpaper.data.repository.WallpaperRepositoryImpl
import com.ilya.wallpaper.domain.repository.WallpaperRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindWallpaperRepository(impl: WallpaperRepositoryImpl): WallpaperRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        @Provides
        @ApplicationScope
        fun provideApiFactory(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(ApiContact.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        @Provides
        @ApplicationScope
        fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)


    }
}