package com.example.recipes.domain

import com.example.recipes.data.recipedetails.RecipeDetailsRepository
import com.example.recipes.entity.RecipeInformation
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val repository: RecipeDetailsRepository
) {

    suspend operator fun invoke(
        id: Long,
        includeNutrition: Boolean,
        success: (recipe: RecipeInformation) -> Unit,
        fail: (e: Exception) -> Unit
    ) {
        repository.getRecipeDetails(id, includeNutrition, success, fail)
    }
}