package com.example.recipes.presentation.recipedetails.overview

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
class RecipeOverviewViewModel @Inject constructor(
    private val getRecipeDetails: GetRecipeDetailsUseCase
): ViewModel() {

    private val _stateLiveData = MutableLiveData<RecipeOverviewState>()
    val stateLiveData: LiveData<RecipeOverviewState>
        get() = _stateLiveData

    fun setup(id: Long, includeNutrition: Boolean) {
        if (stateLiveData.value?.data == null) getData(id, includeNutrition)
    }

    private fun getData(id: Long, includeNutrition: Boolean) {
        state(RecipeOverviewState(loading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getRecipeDetails(
                id = id,
                includeNutrition = includeNutrition,
                success = {
                    state(RecipeOverviewState(data = it))
                },
                fail = {
                    state(RecipeOverviewState(errorMessage = it.localizedMessage))
                }
            )
        }
    }

    private fun state(state: RecipeOverviewState) {
        _stateLiveData.postValue(state)
    }
}