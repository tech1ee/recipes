package com.example.recipes.presentation.recipedetails.ingredients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.GetRecipeIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeIngredientsViewModel @Inject constructor(
    private val getRecipeIngredients: GetRecipeIngredientsUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<RecipeIngredientsState>()
    val stateLiveData: LiveData<RecipeIngredientsState>
        get() = _stateLiveData

    fun setup(id: Long) {
        if (_stateLiveData.value?.data.isNullOrEmpty()) getIngredients(id)
    }

    private fun getIngredients(id: Long) {
        state(RecipeIngredientsState(loading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getRecipeIngredients(
                id = id,
                success = {
                    state(RecipeIngredientsState(data = it))
                },
                fail = {
                    state(RecipeIngredientsState(errorMessage = it.localizedMessage))
                }
            )
        }
    }

    private fun state(state: RecipeIngredientsState) {
        _stateLiveData.postValue(state)
    }
}