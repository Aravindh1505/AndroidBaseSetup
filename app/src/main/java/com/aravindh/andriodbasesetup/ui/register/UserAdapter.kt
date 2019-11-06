package com.aravindh.andriodbasesetup.ui.register

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aravindh.andriodbasesetup.database.entities.User
import com.aravindh.andriodbasesetup.databinding.AdapterUserBinding

class UserAdapter(private val clickListener: UserListener) :
    ListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(clickListener, user)
    }

    class ViewHolder private constructor(val binding: AdapterUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            clickListener: UserListener,
            user: User
        ) {
            binding.user = user
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding =
                    AdapterUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }


    class UserListener(val clickListener: (userId: Long) -> Unit) {
        fun onClick(user: User) = clickListener(user.userId)
    }

}


