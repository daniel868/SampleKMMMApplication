package com.example.kmmapplication.android.di

import com.example.kmmapplication.datasource.cache.RecipeCache
import com.example.kmmapplication.datasource.network.RecipeService
import com.example.kmmapplication.interactors.recipe_detail.GetRecipe
import com.example.kmmapplication.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache
    ): SearchRecipes {
        return SearchRecipes(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache
    ): GetRecipe {
        return GetRecipe(recipeCache = recipeCache)
    }
}