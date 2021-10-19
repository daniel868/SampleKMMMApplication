package com.example.kmmapplication.datasource.network

import com.example.kmmapplication.domain.model.Recipe

interface RecipeService {
    suspend fun search(
        page: Int,
        query: String
    ): List<Recipe>

    suspend fun get(id:Int):Recipe
}