package com.example.recipes.presentation.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeDetailsViewModel : ViewModel() {

    private var recipeId: Long? = null
    private var recipeTitle: String? = null
    private val _stateLiveData = MutableLiveData<RecipeDetailsState>()
    val stateLiveData: LiveData<RecipeDetailsState>
        get() = _stateLiveData

    fun setup(id: Long) {
        recipeId = id
        emitState()
    }

    fun setRecipeTitle(title: String) {
        recipeTitle = title
        emitState()
    }

    private fun emitState() {
        _stateLiveData.postValue(RecipeDetailsState(
            recipeId = recipeId,
            recipeTitle = recipeTitle
        ))
    }
}