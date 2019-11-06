package com.aravindh.andriodbasesetup.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.aravindh.andriodbasesetup.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("http").build()

        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}