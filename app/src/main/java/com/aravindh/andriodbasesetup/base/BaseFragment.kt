package com.aravindh.andriodbasesetup.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

const val SHARED_PREFERENCE_FILE_NAME = "secret_shared_prefs"

open class BaseFragment : Fragment() {

    //TOAST MESSAGE
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    //NAVIGATION DIRECTIONS
    fun navigationTransaction(navigateTo: NavDirections) {
        view?.findNavController()?.navigate(navigateTo)
    }


}