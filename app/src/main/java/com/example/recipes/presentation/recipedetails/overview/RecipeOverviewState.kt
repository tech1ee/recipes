package com.example.recipes.presentation.recipedetails.overview

import com.example.recipes.entity.RecipeInformation

data class RecipeOverviewState(
    val loading: Boolean = false,
    val data: RecipeInformation? = null,
    val errorMessage: String? = null
)