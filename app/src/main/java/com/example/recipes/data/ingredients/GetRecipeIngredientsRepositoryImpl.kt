package com.example.recipes.data.ingredients

import com.example.recipes.data.RecipeApi
import com.example.recipes.data.ResponseUnsuccessfulException
import com.example.recipes.entity.Ingredient

class GetRecipeIngredientsRepositoryImpl(
    private val api: RecipeApi
): GetRecipeIngredientsRepository {

    override suspend fun getRecipeIngredients(
        id: Long,
        success: (ingredients: List<Ingredient>) -> Unit,
        fail: (e: Exception) -> Unit
    ) {
        try {
            val result = api.getRecipeIngredients(id)
            val data = result.body()

            if (result.isSuccessful && data != null) success(data)
            else fail(ResponseUnsuccessfulException())
        } catch (e: Exception) {
            fail(e)
        }
    }
}