package com.aravindh.andriodbasesetup.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aravindh.andriodbasesetup.utils.ErrorCodes
import com.aravindh.andriodbasesetup.utils.Logger

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    //Get values from login form
    var email: String? = null
    var password: String? = null

    //ERROR STATUS
    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate


    private val _randomNumber = MutableLiveData<Int>()
    val randomNumber: LiveData<Int>
        get() = _randomNumber

    init {
        _navigate.value = false
        _randomNumber.value = getRandomNumber()
    }

    fun navigate() {

        //            UiUtils.showMessage(getApplication(), "Email or Password is empty")

        if (email.isNullOrEmpty()) {
            Logger.d("Email is empty")
            _status.value = ErrorCodes.ERROR_EMAIL
            return
        } else if (password.isNullOrEmpty()) {
            Logger.d("Password is empty")
            _status.value = ErrorCodes.ERROR_PASSWORD
        } else {
            _status.value = ErrorCodes.SUCCESS
            Logger.d("Email : $email")
            Logger.d("Password : $password")
        }


//        _randomNumber.value = getRandomNumber()
//        _navigate.value = true
    }


    override fun onCleared() {
        super.onCleared()
        Logger.d("LoginViewModel destroyed!")
    }

    private fun getRandomNumber(): Int {
        return (0..1000).random()
    }
}