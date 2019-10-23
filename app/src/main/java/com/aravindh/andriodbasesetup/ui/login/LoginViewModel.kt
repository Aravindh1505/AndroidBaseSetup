package com.aravindh.andriodbasesetup.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aravindh.andriodbasesetup.utils.Logger

class LoginViewModel(finalScore: Int) : ViewModel() {

    val finalScore = 7

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate


    private val _randomNumber = MutableLiveData<Int>()
    val randomNumber: LiveData<Int>
        get() = _randomNumber

    init {
        Logger.d("LoginViewModel created $finalScore")

        _navigate.value = false
        _randomNumber.value = getRandomNumber()
    }

    fun navigate() {
        _randomNumber.value = getRandomNumber()
        _navigate.value = true
    }


    override fun onCleared() {
        super.onCleared()
        Logger.d("LoginViewModel destroyed!")
    }

    private fun getRandomNumber(): Int {
        return (0..1000).random()
    }
}