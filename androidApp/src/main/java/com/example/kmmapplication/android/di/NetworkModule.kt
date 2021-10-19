package com.example.kmmapplication.android.di

import com.example.kmmapplication.datasource.network.KtorClientFactory
import com.example.kmmapplication.datasource.network.RecipeService
import com.example.kmmapplication.datasource.network.RecipeServiceImpl
import com.example.kmmapplication.datasource.network.RecipeServiceImpl.Companion.BASE_URL
import com.example.kmmapplication.interactors.recipe_detail.GetRecipe
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(httpClient: HttpClient): RecipeService {
        return RecipeServiceImpl(
            httpClient = httpClient,
            baseUrl = BASE_URL
        )
    }

}