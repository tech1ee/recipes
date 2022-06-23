package com.example.recipes.di

import com.example.recipes.data.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRecipeApi(): RecipeApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .build()
        return retrofit.create(RecipeApi::class.java)
    }

}