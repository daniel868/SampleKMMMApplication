package com.example.kmmapplication.datasource.cache

import com.example.kmmapplication.domain.model.Recipe
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory{
    actual fun createDriver():SqlDriver{
        return NativeSqliteDriver(RecipeDatabase.Schema,"recipes.db")
    }
}
