package com.example.recipes.data.recipedetails

import com.example.recipes.entity.RecipeInformation

interface RecipeDetailsRepository {

    suspend fun getRecipeDetails(
        id: Long,
        includeNutrition: Boolean,
        success: (recipe: RecipeInformation) -> Unit,
        fail: (e: Exception) -> Unit
    )
}