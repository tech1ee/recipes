package com.example.recipes.data.ingredients

import com.example.recipes.entity.Ingredient

interface GetRecipeIngredientsRepository {

    suspend fun getRecipeIngredients(
        id: Long,
        success: (ingredients: List<Ingredient>) -> Unit,
        fail: (e: Exception) -> Unit
    )
}