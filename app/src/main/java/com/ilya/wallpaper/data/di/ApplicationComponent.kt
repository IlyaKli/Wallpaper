package com.ilya.wallpaper.data.di

import com.ilya.wallpaper.presentation.screen.wallpaper_list.WallpaperListFragment
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    fun fragmentComponentFactory(): FragmentComponent.Factory

}