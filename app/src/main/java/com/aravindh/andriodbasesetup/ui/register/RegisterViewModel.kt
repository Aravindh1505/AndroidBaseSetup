package com.aravindh.andriodbasesetup.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aravindh.andriodbasesetup.database.dao.UserDao
import com.aravindh.andriodbasesetup.database.entities.User
import com.aravindh.andriodbasesetup.utils.ErrorCodes
import com.aravindh.andriodbasesetup.utils.Logger
import kotlinx.coroutines.*

class RegisterViewModel(val database: UserDao, application: Application) :
    AndroidViewModel(application) {

    //Get values from login form
    var name: String? = null
    var mobileNumber: String? = null
    var email: String? = null
    var password: String? = null

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var users: LiveData<List<User>>?

    //ERROR STATUS
    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status


    init {
        Logger.d("RegisterViewModel created!")

        users = database.getAllUsers()
    }


    fun register() {
        when {
            name.isNullOrEmpty() -> _status.value = ErrorCodes.ERROR_NAME
            mobileNumber.isNullOrEmpty() -> _status.value = ErrorCodes.ERROR_MOBILE_NUMBER
            email.isNullOrEmpty() -> _status.value = ErrorCodes.ERROR_EMAIL
            password.isNullOrEmpty() -> _status.value = ErrorCodes.ERROR_PASSWORD
            else -> uiScope.launch {
                val user = User(
                    userName = name,
                    userMobileNumber = mobileNumber,
                    userEmailAddress = email,
                    userPassword = password
                )
                insert(user)
                _status.value = ErrorCodes.SUCCESS
            }
        }
    }

    private suspend fun insert(user: User) {
        withContext(Dispatchers.IO) {
            database.insert(user)
        }
    }

    fun clearFormFields() {
        name = null
        mobileNumber = null
        email = null
        password = null
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}