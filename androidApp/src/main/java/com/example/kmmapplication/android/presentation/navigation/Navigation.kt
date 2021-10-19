package com.example.kmmapplication.android.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmmapplication.android.presentation.recipe_detail.RecipeDetailScreen
import com.example.kmmapplication.android.presentation.recipe_detail.RecipeDetailViewModel
import com.example.kmmapplication.android.presentation.recipe_list.RecipeListScreen
import com.example.kmmapplication.android.presentation.recipe_list.RecipeListViewModel

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)

            LocalViewModelStoreOwner.current?.let {
                val viewModel: RecipeListViewModel = viewModel(it, "RecipeListViewModel", factory)
                RecipeListScreen(
                    state = viewModel.state.value,
                    onTriggerEvent = viewModel::onTriggerEvent,
                    onClickRecipeListItem = { recipeId ->
                        navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                    }
                )
            }
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)

            LocalViewModelStoreOwner.current?.let {
                val viewModel: RecipeDetailViewModel =
                    viewModel(it, "RecipeDetailViewModel", factory)
                RecipeDetailScreen(recipe = viewModel.recipe.value)
            }
        }
    }
}