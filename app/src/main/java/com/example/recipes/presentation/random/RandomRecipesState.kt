package com.example.recipes.presentation.random

import com.example.recipes.entity.RecipeInformation

data class RandomRecipesState(
    val loading: Boolean = false,
    val recipes: List<RecipeInformation>? = null,
    val errorMessage: String? = null
)
