package com.example.recipes.di

import com.example.recipes.data.RecipeApi
import com.example.recipes.data.random.GetRandomRecipesRepository
import com.example.recipes.data.random.GetRandomRecipesRepositoryImpl
import com.example.recipes.data.recipedetails.RecipeDetailsRepository
import com.example.recipes.data.recipedetails.RecipeDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGetRandomRecipesRepository(api: RecipeApi): GetRandomRecipesRepository {
        return GetRandomRecipesRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideRecipeDetailsRepository(api: RecipeApi): RecipeDetailsRepository {
        return RecipeDetailsRepositoryImpl(api)
    }
}