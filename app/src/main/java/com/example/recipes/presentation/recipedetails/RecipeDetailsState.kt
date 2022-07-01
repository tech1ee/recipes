package com.example.recipes.presentation.recipedetails

import com.example.recipes.entity.RecipeInformation

data class RecipeDetailsState(
    val loading: Boolean = false,
    val data: RecipeInformation? = null,
    val errorMessage: String? = null
)