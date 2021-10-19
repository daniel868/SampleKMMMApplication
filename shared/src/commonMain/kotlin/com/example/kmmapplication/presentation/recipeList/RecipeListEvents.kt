package com.example.kmmapplication.presentation.recipeList

//TODO: Load the recipe
//TODO: Trigger Pagination
//TODO: New Search
sealed class RecipeListEvents {
    object LoadRecipe : RecipeListEvents()

    object NextPage : RecipeListEvents()

    object NewSearch : RecipeListEvents()

    data class OnUpdateQuery(val query: String) : RecipeListEvents()

    data class OnSelectedCategory(val category: FoodCategory) : RecipeListEvents()
}
