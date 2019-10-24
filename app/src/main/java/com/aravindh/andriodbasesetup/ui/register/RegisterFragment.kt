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



        val users = userDao.getAllUsers()
        users.observe(this, Observer {
            Logger.d("List size ${it.size}")

            if (it.isNotEmpty()) {
                for (user in it) {
                    Logger.d(user.userName)
                }
            }
        })


        /*viewModel.users?.observe(this, Observer { users ->
            Logger.d("List size ${users.size}")

            if (users.isNotEmpty()) {
                for (user in users){
                    Logger.d("userName : ${user.userName}")
                }
            }
        })*/


        return binding.root
    }
}
