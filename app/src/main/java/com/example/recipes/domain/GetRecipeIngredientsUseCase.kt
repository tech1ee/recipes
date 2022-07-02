package com.example.recipes.domain

import com.example.recipes.data.ingredients.GetRecipeIngredientsRepository
import com.example.recipes.entity.Ingredient
import javax.inject.Inject

class GetRecipeIngredientsUseCase @Inject constructor(
    private val repository: GetRecipeIngredientsRepository
) {

    suspend operator fun invoke(
        id: Long,
        success: (ingredients: List<Ingredient>) -> Unit,
        fail: (e: Exception) -> Unit
    ) {
        repository.getRecipeIngredients(id, success, fail)
    }
}