package com.ilya.wallpaper.data.di

import android.app.Application
import com.ilya.wallpaper.presentation.screen.wallpaper_list.WallpaperListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: WallpaperListFragment)

    @Component.Factory
    interface Factory {

        fun crate(@BindsInstance application: Application): ApplicationComponent
    }
}