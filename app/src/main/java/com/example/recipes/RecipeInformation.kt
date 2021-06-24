package com.example.recipes

data class RecipeInformation(
    val id: Long?,
    val title: String?,
    val image: String?,
    val imageType: String?,
    val servings: Int?,
    val readyInMinutes: Int?,
    val license: String?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularSourceUrl: String?,
    val aggregateLikes: Int?,
    val healthScore: Double?,
    val spoonacularScore: Double?,
    val pricePerServing: Double?,
    val dairyFree: Boolean?,
    val gaps: String?,
    val glutenFree: Boolean?,
    val instructions: String?,
    val ketogenic: Boolean?,
    val lowFodmap: Boolean?,
    val sustainable: Boolean?,
    val vegan: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val whole30: Boolean?,
    val weightWatcherSmartPoints: Int?,
    val dishTypes: List<String>?,
    val extendedIngredients: List<IngredientExtended>,
    val summary: String?,
    val winePairing: Map<String, List<String>>?
)
