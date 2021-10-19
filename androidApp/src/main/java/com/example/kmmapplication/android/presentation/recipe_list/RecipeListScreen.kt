package com.example.kmmapplication.android.presentation.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmmapplication.android.presentation.components.GradientDemo
import com.example.kmmapplication.android.presentation.components.RecipeCard
import com.example.kmmapplication.android.presentation.components.RecipeList
import com.example.kmmapplication.android.presentation.components.SearchBar
import com.example.kmmapplication.android.presentation.theme.AppTheme
import com.example.kmmapplication.domain.model.Recipe
import com.example.kmmapplication.presentation.recipeList.FoodCategoryUtil
import com.example.kmmapplication.presentation.recipeList.RecipeListEvents
import com.example.kmmapplication.presentation.recipeList.RecipeListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit
) {
    val categories = remember { FoodCategoryUtil().getAllFoodCategories() }
    AppTheme(displayProgressBar = state.isLoading) {
        Scaffold(
            topBar = {
                SearchBar(
                    query = state.query,
                    onQueryChange = {
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                    },
                    categories = categories,
                    onExecuteSearch = {
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    },
                    selectedCategory = state.selectedCategory,
                    onSelectedCategoryChanged = {
                        onTriggerEvent(RecipeListEvents.OnSelectedCategory(it))
                    }
                )
            }
        ) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeListItem
            )
        }
    }
}