package com.ilya.wallpaper.presentation.screen.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya.wallpaper.R
import com.ilya.wallpaper.databinding.FragmentCategoryBinding
import com.ilya.wallpaper.presentation.adapter.category.CategoryRAdapter
import com.ilya.wallpaper.presentation.screen.wallpaper_list.WallpaperListFragment


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding: FragmentCategoryBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoryBinding == null")

    private val adapter by lazy { CategoryRAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryRecyclerView.adapter = adapter
        adapter.categoryClickListener = {
            launchFragment(it.name)
            Log.d("clickCategory", it.toString())
        }
    }

    private fun launchFragment(name: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainerView, WallpaperListFragment.newInstance(name))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() = CategoryFragment()
    }
}