package com.mak.foodiepal.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mak.foodiepal.Recipe

class RecipeViewModel : ViewModel() {
    private val _recipeObserver: MutableLiveData<Recipe> = MutableLiveData<Recipe>()
    val recipeObserver: MutableLiveData<Recipe>
        get() = _recipeObserver

    fun setRecipe(recipe: Recipe) {
        _recipeObserver.value = recipe
    }
}