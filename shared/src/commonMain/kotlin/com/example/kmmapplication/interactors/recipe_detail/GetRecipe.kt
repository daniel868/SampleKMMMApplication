package com.example.kmmapplication.interactors.recipe_detail

import com.example.kmmapplication.datasource.cache.RecipeCache
import com.example.kmmapplication.datasource.network.RecipeService
import com.example.kmmapplication.domain.model.Recipe
import com.example.kmmapplication.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache
) {
    fun execute(
        recipeId: Int
    ): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())
        try {

            val recipe = recipeCache.get(recipeId)

            emit(DataState.data(message = null,data = recipe))

        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown error"))
        }

    }
}