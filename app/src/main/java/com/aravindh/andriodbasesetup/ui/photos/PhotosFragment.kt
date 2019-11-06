package com.aravindh.andriodbasesetup.ui.photos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.aravindh.andriodbasesetup.base.BaseFragment
import com.aravindh.andriodbasesetup.databinding.FragmentPhotosBinding
import com.aravindh.andriodbasesetup.utils.Logger
import com.aravindh.andriodbasesetup.utils.getViewModel

class PhotosFragment : BaseFragment() {

    private val viewModel: PhotosViewModel by lazy {
        getViewModel { PhotosViewModel() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPhotosBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.photosViewModel = viewModel

        val manager = GridLayoutManager(activity, 2)
        binding.photosRecyclerView.layoutManager = manager

        val adapter = PhotosAdapter(PhotoListener {
            showToast(it)
        })

        binding.photosRecyclerView.adapter = adapter

        viewModel.photosList.observe(this, Observer {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        })


        viewModel.status.observe(this, Observer {
            Logger.d("status : $it")
        })


        return binding.root
    }


}
