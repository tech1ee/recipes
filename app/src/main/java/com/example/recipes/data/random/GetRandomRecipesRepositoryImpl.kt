package com.example.recipes.data.random

import com.example.recipes.data.RecipeApi
import com.example.recipes.data.ResponseUnsuccessfulException
import com.example.recipes.entity.RecipeInformation

class GetRandomRecipesRepositoryImpl(
    private val api: RecipeApi
) : GetRandomRecipesRepository {

    override suspend fun getRandomRecipes(
        limitLicense: Boolean,
        tags: List<String>,
        number: Int,
        success: (recipes: List<RecipeInformation>) -> Unit,
        fail: (e: Exception) -> Unit
    ) {
        try {
            val result = api.getRandomRecipes(limitLicense, tags, number)
            val data = result.body()

            if (result.isSuccessful && data != null) success(data.recipes)
            else fail(ResponseUnsuccessfulException())
        } catch (e: Exception) {
            fail(e)
        }
     }
}