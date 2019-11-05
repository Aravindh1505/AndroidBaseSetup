package com.aravindh.andriodbasesetup.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.base.BaseFragment
import com.aravindh.andriodbasesetup.databinding.FragmentLoginBinding
import com.aravindh.andriodbasesetup.utils.ErrorCodes
import com.aravindh.andriodbasesetup.utils.getViewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application

        val viewModel = getViewModel {
            LoginViewModel(application)
        }

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.status.observe(this, Observer {
            when (it) {
                ErrorCodes.ERROR_EMAIL -> showToast("Invalid email address")
                ErrorCodes.ERROR_PASSWORD -> showToast("Invalid password")
                ErrorCodes.SUCCESS -> {
                    showToast("Success")
                }
            }
        })

        //val args = arguments?.let { LoginFragmentArgs.fromBundle(it) }
        //args?.profileType?.let { showToast(it) }

        return binding.root
    }

}
