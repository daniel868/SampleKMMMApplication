package com.example.kmmapplication.datasource.cache

import com.example.kmmapplication.domain.model.Recipe
import com.example.kmmapplication.domain.utils.DateTimeUtil
import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun Recipe_Entity.toRecipe(): Recipe {
    val dateTimeUtil = DateTimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsToList(),
        dateAdded = dateTimeUtil.toLocalDate(date_added),
        dateUpdated = dateTimeUtil.toLocalDate(date_updated)
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
    return map { it.toRecipe() }
}

fun List<String>.convertIngredientToString(): String {
    val ingredientString = StringBuilder()
    for (ingredients in this) {
        ingredientString.append("$ingredients,")
    }
    return ingredientString.toString()
}

fun String.convertIngredientsToList(): List<String> {
    val list: ArrayList<String> = ArrayList()
    for (ingredient in split(",")) {
        list.add(ingredient)
    }
    return list
}
