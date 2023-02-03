package com.ilya.wallpaper.presentation.screen.wallpaper_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ilya.wallpaper.R
import com.ilya.wallpaper.databinding.FragmentCategoryBinding
import com.ilya.wallpaper.databinding.FragmentWallpaperListBinding
import com.ilya.wallpaper.presentation.adapter.category.CategoryRAdapter
import com.ilya.wallpaper.presentation.adapter.wallpaper.WallpaperRAdapter

class WallpaperListFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this)[WallpaperListViewModel::class.java] }

    private var _binding: FragmentWallpaperListBinding? = null
    private val binding: FragmentWallpaperListBinding
        get() = _binding ?: throw RuntimeException("FragmentWallpaperListBinding == null")

    private val adapter by lazy { WallpaperRAdapter() }

    private var categoryName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            categoryName = it.getString(ARG_PARAM1)
            viewModel.loadWallpaper(categoryName!!)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWallpaperListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wallpaperRecyclerView.adapter = adapter
        viewModel.wallpapers.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {

        private const val ARG_PARAM1 = "category_name"

        @JvmStatic
        fun newInstance(categoryName: String) =
            WallpaperListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, categoryName)
                }
            }
    }
}