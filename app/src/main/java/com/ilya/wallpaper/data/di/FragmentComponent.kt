package com.ilya.wallpaper.data.di

import com.ilya.wallpaper.presentation.screen.wallpaper_list.WallpaperListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [ViewModelModule::class]
)
interface FragmentComponent {

    fun inject(fragment: WallpaperListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance
            categoryName: String
        ): FragmentComponent
    }


}