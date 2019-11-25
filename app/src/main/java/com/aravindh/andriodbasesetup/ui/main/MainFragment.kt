package com.aravindh.andriodbasesetup.ui.main


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.base.BaseFragment
import com.aravindh.andriodbasesetup.databinding.FragmentMainBinding
import com.aravindh.andriodbasesetup.utils.Logger

/**
 * A simple [Fragment] subclass.
 */

const val EDIT_TEXT_VALUE = "edit_text_value"

class MainFragment : BaseFragment() {

    private lateinit var myLifeCycleOwner: MyLifeCycleOwner
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        myLifeCycleOwner = MyLifeCycleOwner(this.lifecycle)

        setHasOptionsMenu(true)


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
