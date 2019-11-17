package com.aravindh.andriodbasesetup.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aravindh.andriodbasesetup.databinding.AdapterFoodBinding
import com.aravindh.andriodbasesetup.ui.movie.model.Fnblist
import com.aravindh.andriodbasesetup.ui.movie.model.Food

class FoodAdapter(private val clickListener: ClickListener) :
    ListAdapter<Fnblist, FoodAdapter.ViewHolder>(FoodDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class ViewHolder private constructor(val binding: AdapterFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            clickListener: ClickListener,
            fnblist: Fnblist
        ) {
            binding.food = fnblist
            binding.clickListner = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding =
                    AdapterFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

}


class FoodDiffUtilCallback : DiffUtil.ItemCallback<Fnblist>() {
    override fun areItemsTheSame(oldItem: Fnblist, newItem: Fnblist): Boolean {
        return oldItem.Cinemaid == newItem.Cinemaid
    }

    override fun areContentsTheSame(oldItem: Fnblist, newItem: Fnblist): Boolean {
        return oldItem == newItem
    }
}

class ClickListener(val clickListener: (food: Food) -> Unit) {
    fun onClick(food: Food) = clickListener(food)
}
