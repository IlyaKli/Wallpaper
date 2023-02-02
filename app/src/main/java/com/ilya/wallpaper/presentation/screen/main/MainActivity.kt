package com.ilya.wallpaper.presentation.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilya.wallpaper.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment()
    }

    private fun launchFragment() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainerView, CategoryFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}