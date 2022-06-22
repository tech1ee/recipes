package com.example.recipes.data.search

import com.example.recipes.entity.RecipesSearchResult

interface SearchRecipesRepository {

    suspend fun search(
        queryMap: HashMap<String, String>,
        success: (data: RecipesSearchResult) -> Unit,
        fail: (e: Exception) -> Unit
        )

}