package com.example.recipes.data.search

import com.example.recipes.data.RecipeApi
import com.example.recipes.data.ResponseUnsuccessfulException
import com.example.recipes.entity.RecipesSearchResult

class SearchRecipesRepositoryImpl(
    private val api: RecipeApi
): SearchRecipesRepository {

    override suspend fun search(
        queryMap: HashMap<String, String>,
        success: (data: RecipesSearchResult) -> Unit,
        fail: (e: Exception) -> Unit
    ) {
        try {
            val result = api.searchRecipes(queryMap)
            val data = result.body()

            if (result.isSuccessful && data != null) success(data)
            else fail(ResponseUnsuccessfulException())
        } catch (e: Exception) {
            fail(e)
        }
    }
}