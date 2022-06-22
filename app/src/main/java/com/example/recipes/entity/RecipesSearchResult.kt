package com.example.recipes.entity

import com.example.recipes.entity.RecipeItem

data class RecipesSearchResult(
    val offset: Int?,
    val number: Int?,
    val results: List<RecipeItem>
)
