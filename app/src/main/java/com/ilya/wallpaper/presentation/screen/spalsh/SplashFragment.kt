package com.ilya.wallpaper.presentation.screen.spalsh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilya.wallpaper.R
import com.ilya.wallpaper.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding ?: throw RuntimeException("FragmentSplashBinding == null")

    private val scope by lazy { CoroutineScope(Dispatchers.Main.immediate) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scope.launch {
            delay(1000)
            launchCategoryFragment()
        }
    }

    override fun onStop() {
        super.onStop()
        scope.cancel()
    }

    private fun launchCategoryFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_categoryFragment)
    }
}