package com.ilya.wallpaper.presentation.screen.wallpaper_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ilya.wallpaper.databinding.FragmentWallpaperListBinding
import com.ilya.wallpaper.presentation.adapter.wallpaper.WallpaperRAdapter
import com.ilya.wallpaper.presentation.screen.WallpaperListViewModelFactory

class WallpaperListFragment : Fragment() {

    private val args by navArgs<WallpaperListFragmentArgs>()
    private val categoryName by lazy { args.categoryName }

    private val wallpaperListViewModelFactory by lazy { WallpaperListViewModelFactory(categoryName) }
    private val viewModel by lazy {
        ViewModelProvider(this, wallpaperListViewModelFactory)[WallpaperListViewModel::class.java]
    }

    private var _binding: FragmentWallpaperListBinding? = null
    private val binding: FragmentWallpaperListBinding
        get() = _binding ?: throw RuntimeException("FragmentWallpaperListBinding == null")

    private val adapter by lazy { WallpaperRAdapter() }

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
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.wallpaperSwipeRefreshLayout.isRefreshing = it
        }
        binding.wallpaperSwipeRefreshLayout.setOnRefreshListener {
            viewModel.loadWallpaper()
        }
        adapter.wallpaperClickListener = {
            launchFragment(it.largeImageURL)
        }
    }

    private fun launchFragment(imageURL: String) {
        findNavController().navigate(WallpaperListFragmentDirections
            .actionWallpaperListFragmentToDetailWallpaperFragment2(imageURL))
    }
}