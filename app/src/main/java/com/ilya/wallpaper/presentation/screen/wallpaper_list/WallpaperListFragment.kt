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
import com.ilya.wallpaper.domain.model.Wallpaper
import com.ilya.wallpaper.presentation.adapter.wallpaper.WallpaperRAdapter
import com.ilya.wallpaper.presentation.view_model_factory.WallpaperListViewModelFactory

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
        setRecyclerView()
        setObserve()
        setListener()
    }

    private fun setObserve() {
        with(viewModel) {
            wallpapers.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            loading.observe(viewLifecycleOwner) {
                binding.wallpaperSwipeRefreshLayout.isRefreshing = it
            }
        }
    }

    private fun setRecyclerView() {
        binding.wallpaperRecyclerView.adapter = adapter
    }

    private fun setListener() {
        binding.wallpaperSwipeRefreshLayout.setOnRefreshListener {
            viewModel.loadWallpaper()
        }
        adapter.wallpaperClickListener = {
            launchDetailWallpaperFragment(it.largeImageURL)
        }
        adapter.onReachEndListener = {
            viewModel.loadWallpaper()
        }
    }

    private fun launchDetailWallpaperFragment(imageURL: String) {
        findNavController().navigate(WallpaperListFragmentDirections
            .actionWallpaperListFragmentToDetailWallpaperFragment2(imageURL))
    }
}