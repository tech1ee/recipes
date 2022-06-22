package com.example.recipes.entity

data class RecipeItem(
    val id: Long?,
    val title: String?,
    val calories: Long?,
    val carbs: String?,
    val fat: String?,
    val image: String?,
    val imageType: String?,
    val protein: String?
)