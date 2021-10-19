package com.example.kmmapplication.android.presentation.recipe_detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmapplication.datasource.network.RecipeService
import com.example.kmmapplication.domain.model.Recipe
import com.example.kmmapplication.interactors.recipe_detail.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "RecipeDetailViewModel"

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipe: GetRecipe,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {

        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            getRecipeFromCache(recipeId = recipeId)
        }
    }

    private fun getRecipeFromCache(recipeId: Int) {
        getRecipe.execute(recipeId).onEach { dataState ->
            println("${TAG}: ${dataState.isLoading}")

            dataState.data?.let {
                println("${TAG}: $it")
                this.recipe.value = it
            }

            dataState.message?.let {
                println("${TAG}: $it")
            }
        }.launchIn(viewModelScope)
    }
}