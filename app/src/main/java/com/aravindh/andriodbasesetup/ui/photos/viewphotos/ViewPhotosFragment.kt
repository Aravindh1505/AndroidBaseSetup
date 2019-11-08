package com.aravindh.andriodbasesetup.ui.photos.viewphotos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aravindh.andriodbasesetup.databinding.FragmentViewPhotosBinding
import com.aravindh.andriodbasesetup.utils.getViewModel


class ViewPhotosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPhotosBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val photoArgument = ViewPhotosFragmentArgs.fromBundle(arguments!!).photo

        val viewModel = getViewModel {
            ViewPhotosViewModel(photoArgument,application)
        }

        binding.viewModel = viewModel

        return binding.root
    }


}
