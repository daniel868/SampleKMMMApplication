package com.example.kmmapplication.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmapplication.domain.model.Recipe
import com.example.kmmapplication.interactors.recipe_list.SearchRecipes
import com.example.kmmapplication.presentation.recipeList.FoodCategory
import com.example.kmmapplication.presentation.recipeList.RecipeListEvents
import com.example.kmmapplication.presentation.recipeList.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "RecipeListViewModel"

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val searchRecipes: SearchRecipes
) : ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipe)
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipe -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnSelectedCategory -> {
                onSelectedCategory(event.category)
            }
            else -> {
                handleError("Error Event")
            }
        }
    }

    private fun onSelectedCategory(category: FoodCategory) {
        state.value = state.value.copy(selectedCategory = category, query = category.value)
        newSearch()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes = recipes)
            }

            dataState.message?.let {
                println("$TAG: $it")
            }

        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        //reset the recipes
        loadRecipes()
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val currentList = ArrayList(state.value.recipes)
        currentList.addAll(recipes)
        state.value = state.value.copy(recipes = currentList)
    }

    private fun handleError(message: String) {

    }
}