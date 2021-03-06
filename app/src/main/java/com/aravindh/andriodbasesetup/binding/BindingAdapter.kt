package com.aravindh.andriodbasesetup.binding

import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.network.ApiStatus
import com.aravindh.andriodbasesetup.ui.movie.FoodAdapter
import com.aravindh.andriodbasesetup.ui.movie.model.Fnblist
import com.aravindh.andriodbasesetup.ui.photos.PhotosAdapter
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

@BindingAdapter("listPhotosData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photos>?) {
    val adapter = recyclerView.adapter as PhotosAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(imageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.SUCCESS -> {
            imageView.visibility = View.GONE
        }
        ApiStatus.FAILURE -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}


@BindingAdapter("passwordValidator")
fun passwordValidator(editText: EditText, password: String) {
    val minimumLength = 5
    if (TextUtils.isEmpty(password)) {
        editText.error = "Password can not be empty"
        return
    }
    if (editText.text.toString().length < minimumLength) {
        editText.error = "Password must be minimum $minimumLength length"
    } else
        editText.error = null
}


@BindingAdapter("listFoodData")
fun bindFoodRecyclerView(recyclerView: RecyclerView, data: List<Fnblist>?) {
    val adapter = recyclerView.adapter as FoodAdapter
    adapter.submitList(data)
}