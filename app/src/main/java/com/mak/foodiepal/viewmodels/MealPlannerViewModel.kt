package com.mak.foodiepal.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MealPlannerViewModel : ViewModel() {
    val sharedData = MutableLiveData<Pair<String, String>>()
    fun updateSharedData(data: Pair<String, String>) {
        sharedData.value = data
    }
}