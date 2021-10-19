package com.example.kmmapplication.android.di

import com.example.kmmapplication.android.BaseApplication
import com.example.kmmapplication.datasource.cache.*
import com.example.kmmapplication.domain.utils.DateTimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase,
        dateTimeUtil: DateTimeUtil
    ): RecipeCache {
        return RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            dateTimeUtil = dateTimeUtil
        )
    }

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideDateTimeUtil(): DateTimeUtil {
        return DateTimeUtil()
    }

}