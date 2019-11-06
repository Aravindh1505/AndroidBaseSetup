package com.aravindh.andriodbasesetup.ui.register


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.base.BaseFragment
import com.aravindh.andriodbasesetup.database.MyDatabase
import com.aravindh.andriodbasesetup.databinding.FragmentRegisterBinding
import com.aravindh.andriodbasesetup.utils.ErrorCodes
import com.aravindh.andriodbasesetup.utils.Logger
import com.aravindh.andriodbasesetup.utils.getViewModel

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        val application = requireNotNull(this.activity).application

        val userDao = MyDatabase.getInstance(application).userDao

        val viewModel = getViewModel {
            RegisterViewModel(userDao, application)
        }

        binding.registrationViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = UserAdapter(UserAdapter.UserListener { userId ->
            showToast("User Id : $userId")
        })


        binding.userRecyclerView.adapter = adapter


        viewModel.status.observe(this, Observer {
            when (it) {
                ErrorCodes.ERROR_NAME -> showToast("Invalid name")
                ErrorCodes.ERROR_MOBILE_NUMBER -> showToast("Invalid mobile number")
                ErrorCodes.ERROR_EMAIL -> showToast("Invalid email address")
                ErrorCodes.ERROR_PASSWORD -> showToast("Invalid password")
                ErrorCodes.SUCCESS -> {
                    showToast("User registered successfully")
//                    viewModel.clearFormFields()
                }
            }
        })



        viewModel.users?.observe(this, Observer { users ->
            Logger.d("List size ${users.size}")

            if (users.isNotEmpty()) {
                users.let {
                    adapter.submitList(it)
                }
            }

            /*if (users.isNotEmpty()) {
                for (user in users) {
                    Logger.d("userName : ${user.userName}")
                    Logger.d("userMobileNumber : ${user.userMobileNumber}")
                    Logger.d("userEmailAddress : ${user.userEmailAddress}")
                    Logger.d("userPassword : ${user.userPassword}")
                }
            }*/
        })


        return binding.root
    }
}
