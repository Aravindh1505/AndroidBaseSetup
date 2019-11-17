package com.aravindh.andriodbasesetup.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aravindh.andriodbasesetup.network.API
import com.aravindh.andriodbasesetup.ui.movie.model.Fnblist
import com.aravindh.andriodbasesetup.ui.movie.model.Food
import com.aravindh.andriodbasesetup.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {

    private val _food = MutableLiveData<List<Food>>()
    val food: LiveData<List<Food>>
        get() = _food

    private val _foodList = MutableLiveData<List<Fnblist>>()
    val foodList: LiveData<List<Fnblist>>
        get() = _foodList


    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getFoodList()
    }


    private fun getFoodList() {
        coroutineScope.launch {
            val data = API.retrofitApiService.getFoodsAsync().await()

            Logger.d("foodList : $data")

            _food.value = data.FoodList

            _foodList.value = data.FoodList[0].fnblist


            data.FoodList[0].fnblist[0].subitems[0].Name
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}