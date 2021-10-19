package com.example.kmmapplication.android.presentation.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kmmapplication.android.presentation.components.RecipeImage
import com.example.kmmapplication.android.presentation.components.RecipeView
import com.example.kmmapplication.android.presentation.theme.AppTheme
import com.example.kmmapplication.domain.model.Recipe

@ExperimentalStdlibApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
) {
    AppTheme(displayProgressBar = false) {
        if (recipe == null) {
            Text("Unable to get the details of this recipe...")
        } else {
           RecipeView(recipe = recipe)
        }
    }

}