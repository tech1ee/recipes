package com.example.recipes.presentation.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetails: GetRecipeDetailsUseCase
): ViewModel() {

    private val _stateLiveData = MutableLiveData<RecipeDetailsState>()
    val stateLiveData: LiveData<RecipeDetailsState>
        get() = _stateLiveData

    fun setup(id: Long, includeNutrition: Boolean) {
        if (stateLiveData.value?.data == null) getData(id, includeNutrition)
    }

    private fun getData(id: Long, includeNutrition: Boolean) {
        state(RecipeDetailsState(loading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getRecipeDetails(
                id = id,
                includeNutrition = includeNutrition,
                success = {
                    state(RecipeDetailsState(data = it))
                },
                fail = {
                    state(RecipeDetailsState(errorMessage = it.localizedMessage))
                }
            )
        }
    }

    private fun state(state: RecipeDetailsState) {
        _stateLiveData.postValue(state)
    }
}