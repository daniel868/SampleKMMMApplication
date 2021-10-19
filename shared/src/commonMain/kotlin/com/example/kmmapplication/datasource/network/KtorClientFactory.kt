package com.example.kmmapplication.datasource.network

import com.example.kmmapplication.datasource.network.model.RecipeDto
import com.example.kmmapplication.domain.model.Recipe
import com.example.kmmapplication.domain.utils.DateTimeUtil
import io.ktor.client.*

expect class KtorClientFactory {
    fun build(): HttpClient
}

fun RecipeDto.toRecipe(): Recipe {
    val dateTimeUtil = DateTimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = dateTimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = dateTimeUtil.toLocalDate(longDateUpdated.toDouble())
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe> {
    return map { it.toRecipe() }
}