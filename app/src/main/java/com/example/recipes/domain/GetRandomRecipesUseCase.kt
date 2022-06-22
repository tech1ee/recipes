package com.example.recipes.domain

import com.example.recipes.data.random.GetRandomRecipesRepository
import com.example.recipes.entity.RecipeInformation
import javax.inject.Inject

class GetRandomRecipesUseCase @Inject constructor(
    private val getRandomRecipesRepository: GetRandomRecipesRepository
) {

    suspend operator fun invoke(
        limitLicense: Boolean,
        tags: List<String>,
        number: Int,
        success: (recipes: List<RecipeInformation>) -> Unit,
        fail: (e: Exception) -> Unit
    ) {
        getRandomRecipesRepository.getRandomRecipes(limitLicense, tags, number, success, fail)
    }
}