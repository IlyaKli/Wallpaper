package com.ilya.wallpaper.presentation.di

import android.app.Application
import com.ilya.wallpaper.data.di.DaggerApplicationComponent

class WallpaperApplication: Application() {
    val component by lazy { DaggerApplicationComponent.factory().crate(this) }
}