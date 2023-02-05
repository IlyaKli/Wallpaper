package com.ilya.wallpaper.presentation.screen.spalsh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya.wallpaper.R
import com.ilya.wallpaper.databinding.FragmentDetailWallpaperBinding
import com.ilya.wallpaper.databinding.FragmentSplashBinding
import com.ilya.wallpaper.presentation.screen.main.CategoryFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding ?: throw RuntimeException("FragmentSplashBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main.immediate).launch {
            delay(1000)
            launchFragment()
        }
    }

    private fun launchFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainerView, CategoryFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}