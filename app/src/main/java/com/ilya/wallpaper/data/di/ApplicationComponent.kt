package com.ilya.wallpaper.data.di

import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    fun fragmentComponentFactory(): FragmentComponent.Factory

}