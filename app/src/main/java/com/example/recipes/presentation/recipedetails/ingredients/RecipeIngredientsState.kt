package com.example.recipes.presentation.recipedetails.ingredients

import com.example.recipes.entity.Ingredient

data class RecipeIngredientsState(
    val loading: Boolean = false,
    val data: List<Ingredient>? = null,
    val errorMessage: String? = null
)
