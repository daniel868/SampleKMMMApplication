package com.example.kmmapplication.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.kmmapplication.android.presentation.navigation.Navigation
import com.example.kmmapplication.datasource.network.KtorClientFactory
import com.example.kmmapplication.datasource.network.RecipeServiceImpl
import com.example.kmmapplication.datasource.network.RecipeServiceImpl.Companion.BASE_URL
import com.example.kmmapplication.datasource.network.model.RecipeDto
import com.example.kmmapplication.datasource.network.toRecipe
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }

}


