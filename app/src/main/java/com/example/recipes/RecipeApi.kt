package com.example.recipes

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/{id}/information")
    fun getRecipeInformation(
        @Path("id") id: Long,
        @Query("includeNutrition") includeNutrition: Boolean
    ): RecipeInformation
}