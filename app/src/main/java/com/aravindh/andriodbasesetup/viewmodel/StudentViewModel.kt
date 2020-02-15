package com.aravindh.andriodbasesetup.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.aravindh.andriodbasesetup.database.MyDatabase
import com.aravindh.andriodbasesetup.utils.Logger


/**
 *Created by Aravindh S on 11-02-2020.
 */

class StudentViewModel(val database: MyDatabase) : ViewModel() {

    var id: ObservableField<String> = ObservableField()
    var name: ObservableField<String> = ObservableField()
    var emailAddress: ObservableField<String> = ObservableField()
    var mobileNumber: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()


    fun onSubmitClick() {
        Logger.d("clicked...!")
    }

}