package com.example.kmmapplication.android.di

import android.content.Context
import com.example.kmmapplication.android.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicatin(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication;
    }
}