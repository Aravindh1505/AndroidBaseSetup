package com.aravindh.andriodbasesetup.ui.photos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.aravindh.andriodbasesetup.base.BaseFragment
import com.aravindh.andriodbasesetup.database.MyDatabase
import com.aravindh.andriodbasesetup.databinding.FragmentPhotosBinding
import com.aravindh.andriodbasesetup.utils.Logger
import com.aravindh.andriodbasesetup.utils.getViewModel

class PhotosFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPhotosBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        val photosDao = MyDatabase.getInstance(application).photosDao

        val viewModel: PhotosViewModel by lazy {
            getViewModel { PhotosViewModel(photosDao, application) }
        }

        binding.lifecycleOwner = this
        binding.photosViewModel = viewModel


        val adapter = PhotosAdapter(PhotoListener {
            Logger.d("photoPath :$it")

            navigationTransaction(
                PhotosFragmentDirections.actionPhotosFragmentToViewPhotosFragment(
                    it
                )
            )
        })

        binding.photosRecyclerView.adapter = adapter

        viewModel.status.observe(this, Observer {
            Logger.d("status : $it")
        })


        return binding.root
    }


}
