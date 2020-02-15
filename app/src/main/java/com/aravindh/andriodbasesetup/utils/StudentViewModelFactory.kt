package com.aravindh.andriodbasesetup.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aravindh.andriodbasesetup.database.MyDatabase
import com.aravindh.andriodbasesetup.viewmodel.StudentViewModel


/**
 *Created by Aravindh S on 11-02-2020.
 */

class StudentViewModelFactory(val database: MyDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentViewModel(database) as T
    }
}