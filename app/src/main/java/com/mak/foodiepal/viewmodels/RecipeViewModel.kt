package com.mak.foodiepal.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mak.foodiepal.Recipe

class RecipeViewModel : ViewModel() {
    private val _recipeObserver: MutableLiveData<Recipe> = MutableLiveData<Recipe>()
    val recipeObserver: LiveData<Recipe>
        get() = _recipeObserver

    private val _title: MutableLiveData<String> = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    fun setRecipe(recipe: Recipe) {
        _recipeObserver.value = recipe
        Log.e("AIUB", "${recipeObserver.value?.recipeName} STEP 2")

        //Working: Recipe Data is in LiveData.
        Log.e("AIUB", "${recipeObserver.value} STEP 3")
    }

    fun setTitle(ss: String) {
        _title.value = ss
        Log.e("AIUB", "${title.value} STEP 2.2")
    }
}