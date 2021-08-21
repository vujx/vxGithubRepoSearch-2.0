package com.algebra.githubreposearch20.presentation.ui.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.algebra.githubreposearch20.R
import com.bumptech.glide.Glide

class ImageHelper {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(
            view: ImageView,
            url: String
        ) {
            Glide.with(view)
                .load(url)
                .skipMemoryCache(false)
                .placeholder(R.drawable.imagenotavaliable)
                .error(R.drawable.imagenotavaliable)
                .into(view)
        }
    }
}
