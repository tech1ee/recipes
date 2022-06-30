package com.example.recipes.data.recipedetails

import com.example.recipes.data.RecipeApi
import com.example.recipes.data.ResponseUnsuccessfulException
import com.example.recipes.entity.RecipeInformation

class RecipeDetailsRepositoryImpl(
    private val api: RecipeApi
): RecipeDetailsRepository {

    override suspend fun getRecipeDetails(
        id: Long,
        includeNutrition: Boolean,
        success: (recipe: RecipeInformation) -> Unit,
        fail: (e: Exception) -> Unit
    ) {
        try {
            val result = api.getRecipeInformation(id, includeNutrition)
            val data = result.body()

            if (result.isSuccessful && data != null) success(data)
            else fail(ResponseUnsuccessfulException())
        } catch (e: Exception) {
            fail(e)
        }
    }
}