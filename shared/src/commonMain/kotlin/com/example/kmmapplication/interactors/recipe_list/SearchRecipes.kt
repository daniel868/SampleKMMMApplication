package com.example.kmmapplication.interactors.recipe_list

import android.util.Log
import com.example.kmmapplication.datasource.cache.RecipeCache
import com.example.kmmapplication.datasource.network.RecipeService
import com.example.kmmapplication.domain.model.Recipe
import com.example.kmmapplication.domain.utils.DataState
import io.ktor.util.pipeline.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "SearchRecipes"

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {
    fun execute(
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>> = flow {
        //how can we emit loading?
        try {
            emit(DataState.loading())

            val recipes = try {
                recipeService.search(page, query)
            } catch (e: Exception) {
                Log.e(TAG, "Can't execute search method", e)
                recipeCache.getAll(page)
            }

            recipeCache.insert(recipes);

            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(query, page)
            }

            emit(DataState.data(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(message = e.message ?: "Unknown Error"))
        }
    }
}

