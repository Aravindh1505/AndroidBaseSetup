package com.aravindh.andriodbasesetup.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.databinding.AdapterPhotosBinding

class PhotosAdapter(private val clickListener: PhotoListener) :
    ListAdapter<Photos, PhotosAdapter.ViewHolder>(PhotosDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class ViewHolder private constructor(val binding: AdapterPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            clickListener: PhotoListener,
            photos: Photos
        ) {
            binding.photos = photos
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding =
                    AdapterPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

}


class PhotosDiffUtilCallback : DiffUtil.ItemCallback<Photos>() {
    override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem.photoName == newItem.photoName
    }

    override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem == newItem
    }
}

class PhotoListener(val clickListener: (photo: Photos) -> Unit) {
    fun onClick(photos: Photos) = clickListener(photos)
}