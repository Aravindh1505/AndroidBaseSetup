package com.aravindh.andriodbasesetup.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aravindh.andriodbasesetup.database.User
import com.aravindh.andriodbasesetup.database.UserDao
import com.aravindh.andriodbasesetup.utils.Logger
import kotlinx.coroutines.*

class RegisterViewModel(val database: UserDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var users: LiveData<List<User>>? = null


    init {
        Logger.d("RegisterViewModel created!")
    }

    fun register() {
        uiScope.launch {
            val user = User(
                userName = "Aravindh",
                userMobileNumber = 9791779068,
                userEmailAddress = "aravindh.s@tvsnext.io",
                userPassword = "Test123@"
            )
            insert(user)
        }
    }

    private suspend fun insert(user: User) {
        withContext(Dispatchers.IO) {
            database.insert(user)
        }
    }

    fun users() {
        uiScope.launch {
            users = getAllUsers()
        }
    }

    suspend fun getAllUsers(): LiveData<List<User>>? {
        var users: LiveData<List<User>>? = null

        withContext(Dispatchers.IO) {
            users = database.getAllUsers()
        }
        return users
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}