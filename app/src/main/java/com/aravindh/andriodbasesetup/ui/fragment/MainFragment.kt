package com.aravindh.andriodbasesetup.ui.fragment


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.base.BaseFragment
import com.aravindh.andriodbasesetup.databinding.FragmentMainBinding
import com.aravindh.andriodbasesetup.ui.adapter.MainAdapter
import com.aravindh.andriodbasesetup.ui.adapter.MainClickListener
import com.aravindh.andriodbasesetup.utils.Logger
import com.aravindh.andriodbasesetup.utils.MainLifeCycleOwner
import com.aravindh.andriodbasesetup.utils.SharedPref
import com.aravindh.andriodbasesetup.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass.
 */

const val EDIT_TEXT_VALUE = "edit_text_value"

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        val mainLifeCycleOwner =
            MainLifeCycleOwner(this.lifecycle)
        setHasOptionsMenu(true)

        val adapter = MainAdapter(
            MainClickListener {
                Logger.d(it.photoPath)
            })

        binding.recyclerViewPhotos.adapter = adapter

        viewModel.photos.observe(this, Observer {
            Logger.d(it.size.toString())
            adapter.submitList(it)
        })



        val sharedPref = context?.let { SharedPref().getSharedPref(it) }



        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item, view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Logger.w("onSaveInstanceState called...")
        //outState.putString(EDIT_TEXT_VALUE, binding.messageEditText.text.toString().trim())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Logger.w("onViewStateRestored called...")
    }


}
