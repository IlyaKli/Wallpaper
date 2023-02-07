package com.ilya.wallpaper.presentation.screen.wallpaper_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ilya.wallpaper.databinding.FragmentWallpaperListBinding
import com.ilya.wallpaper.presentation.adapter.wallpaper.WallpaperRAdapter
import com.ilya.wallpaper.presentation.di.ViewModelFactory
import com.ilya.wallpaper.presentation.di.WallpaperApplication
import javax.inject.Inject

class WallpaperListFragment : Fragment() {

    private val args by navArgs<WallpaperListFragmentArgs>()
    private val categoryName by lazy { args.categoryName }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val wallpaperListViewModel by lazy { ViewModelProvider(this, viewModelFactory)[WallpaperListViewModel::class.java] }

    private val component by lazy { (requireActivity().application as WallpaperApplication)
        .component
        .fragmentComponentFactory()
        .create(categoryName)}

    private var _binding: FragmentWallpaperListBinding? = null
    private val binding: FragmentWallpaperListBinding
        get() = _binding ?: throw RuntimeException("FragmentWallpaperListBinding == null")

    private val listAdapter by lazy { WallpaperRAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
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
        setRecyclerView()
        setObserve()
        setListener()
    }

    private fun setObserve() {
        with(wallpaperListViewModel) {
            wallpapers.observe(viewLifecycleOwner) {
                listAdapter.submitList(it)
            }
            loading.observe(viewLifecycleOwner) {
                binding.wallpaperSwipeRefreshLayout.isRefreshing = it
            }
            error.observe(viewLifecycleOwner) {
                if (it == null) {
                    return@observe
                } else {
                    Toast.makeText(
                        requireContext(),
                        it,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setRecyclerView() {
        binding.wallpaperRecyclerView.adapter = listAdapter
    }

    private fun setListener() {
        binding.wallpaperSwipeRefreshLayout.setOnRefreshListener {
            wallpaperListViewModel.loadWallpaper()
        }
        listAdapter.wallpaperClickListener = {
            launchDetailWallpaperFragment(it.largeImageURL)
        }
        listAdapter.onReachEndListener = {
            wallpaperListViewModel.loadWallpaper()
        }
    }

    private fun launchDetailWallpaperFragment(imageURL: String) {
        findNavController().navigate(WallpaperListFragmentDirections
            .actionWallpaperListFragmentToDetailWallpaperFragment2(imageURL))
    }
}