package com.aravindh.andriodbasesetup.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aravindh.andriodbasesetup.databinding.FragmentLoginBinding
import com.aravindh.andriodbasesetup.model.LoginRequest
import com.aravindh.andriodbasesetup.network.API
import com.aravindh.andriodbasesetup.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var coroutineScope : CoroutineScope
    private var job: Job = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

        coroutineScope = CoroutineScope(job)

        coroutineScope.launch {
            login()
        }



        return binding.root
    }

    suspend fun login() {
        val loginRequest = LoginRequest("rcmuser", "rcm123")

        try {
            val response = API.retrofitApiService.loginAsync(loginRequest).await()

            Logger.d(response.auth_token.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
