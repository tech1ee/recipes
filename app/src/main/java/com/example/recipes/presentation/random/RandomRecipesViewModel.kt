package com.example.recipes.presentation.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.GetRandomRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomRecipesViewModel @Inject constructor(
    private val getRandomRecipes: GetRandomRecipesUseCase
): ViewModel() {

    private val _stateLiveData = MutableLiveData<RandomRecipesState>()
    val stateLiveData: LiveData<RandomRecipesState>
        get() = _stateLiveData

    private var limitLicense = false
    private val tags = mutableListOf<String>()
    private val number = 100

    fun getRandomRecipes() {
        state(RandomRecipesState(loading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getRandomRecipes(
                limitLicense = limitLicense,
                tags = tags,
                number = number,
                success = {
                    state(RandomRecipesState(recipes = it))
                },
                fail = {
                    state(RandomRecipesState(errorMessage = it.localizedMessage))
                }
            )
        }
    }

    private fun state(state: RandomRecipesState) {
        _stateLiveData.postValue(state)
    }
}