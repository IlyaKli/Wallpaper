package com.ilya.wallpaper.data.di

import androidx.lifecycle.ViewModel
import com.ilya.wallpaper.presentation.screen.wallpaper_list.WallpaperListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WallpaperListViewModel::class)
    fun bindCoinViewModel(viewModel: WallpaperListViewModel): ViewModel
}