package com.example.recipes.data.random

import com.example.recipes.entity.RecipeInformation

interface GetRandomRecipesRepository {

    suspend fun getRandomRecipes(
        limitLicense: Boolean,
        tags: List<String>,
        number: Int,
        success: (recipes: List<RecipeInformation>) -> Unit,
        fail: (e: Exception) -> Unit
    )
}