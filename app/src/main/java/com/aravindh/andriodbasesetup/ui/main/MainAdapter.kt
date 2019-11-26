package com.aravindh.andriodbasesetup.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.databinding.AdapterMainBinding

class MainAdapter(val clickListener: MainClickListener) :
    ListAdapter<Photos, MainAdapter.ViewHolder>(MainDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }


    class ViewHolder private constructor(val binding: AdapterMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: MainClickListener, photos: Photos) {
            binding.photos = photos
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding =
                    AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

}


class MainDiffUtilCallback : DiffUtil.ItemCallback<Photos>() {
    override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem.photoPath == newItem.photoPath
    }

    override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem == newItem
    }
}


class MainClickListener(val clickListener: (photo: Photos) -> Unit) {
    fun onClick(photo: Photos) = clickListener(photo)
}

