package com.ilya.wallpaper.presentation.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilya.wallpaper.R
import com.ilya.wallpaper.databinding.FragmentCategoryBinding
import com.ilya.wallpaper.presentation.adapter.category.CategoryRAdapter


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding: FragmentCategoryBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoryBinding == null")

    private val adapter by lazy { CategoryRAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryRecyclerView.adapter = adapter
        adapter.categoryClickListener = {
            launchFragment(it.name)
        }
    }

    private fun launchFragment(categoryName: String) {
        findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToWallpaperListFragment(categoryName))
    }
}