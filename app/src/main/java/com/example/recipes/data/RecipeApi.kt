package com.example.recipes.data

import com.example.recipes.entity.RecipeInformation
import com.example.recipes.entity.RecipesSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RecipeApi {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("limitLicense") limitLicense: Boolean,
        @Query("tags") tags: List<String>,
        @Query("number") number: Int
    ): Response<List<RecipeInformation>>

    @GET("recipes/complexSearch")
    fun searchRecipes(@QueryMap queryMap: HashMap<String, String>): Response<RecipesSearchResult>

    @GET("recipes/{id}/information")
    fun getRecipeInformation(
        @Path("id") id: Long,
        @Query("includeNutrition") includeNutrition: Boolean
    ): Response<RecipeInformation>
}