package com.ilya.wallpaper.presentation.screen.wallpaper_detail

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ilya.wallpaper.databinding.FragmentDetailWallpaperBinding
import java.io.ByteArrayOutputStream


class DetailWallpaperFragment : Fragment() {

    private var _binding: FragmentDetailWallpaperBinding? = null
    private val binding: FragmentDetailWallpaperBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailWallpaperBinding == null")

    private val args by navArgs<DetailWallpaperFragmentArgs>()
    private val imageURL by lazy { args.imageURL }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailWallpaperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setListener()
    }

    private fun setListener() {
        binding.setWallpaperButton.setOnClickListener {
            createWallpaperManagerIntent()
        }
    }

    private fun setView() {
        with(binding) {
            setWallpaperButton.isEnabled = false
            detailProgressBar.visibility = View.VISIBLE

            Glide
                .with(detailImageView)
                .asBitmap()
                .load(imageURL)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        detailProgressBar.visibility = View.GONE
                        setWallpaperButton.isEnabled = true
                        return false
                    }
                })
                .into(detailImageView)
        }
    }


    private fun createWallpaperManagerIntent() {
        val bitmap = binding.detailImageView.drawable.toBitmap()

        val myWallpaperManager =
            WallpaperManager.getInstance(requireActivity())

        val myImageUri = getImageUri(requireActivity(), bitmap)
        val intent = Intent(myWallpaperManager.getCropAndSetWallpaperIntent(myImageUri))
        startActivity(intent)

    }

    private fun getImageUri(context: Context, image: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        image.compress(
            Bitmap.CompressFormat.JPEG,
            100,
            bytes
        )
        val path = Images.Media.insertImage(
            context.contentResolver,
            image, "Title", null
        )
        return Uri.parse(path)
    }
}