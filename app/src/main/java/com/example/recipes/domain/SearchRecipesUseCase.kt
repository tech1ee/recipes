package com.example.recipes.domain

import com.example.recipes.data.search.SearchRecipesRepository
import retrofit2.http.QueryMap
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val searchRecipesRepository: SearchRecipesRepository
) {

    suspend operator fun invoke(queryMap: HashMap<String, String>) {
        //TODO make query map with Enums and implement
    }
}