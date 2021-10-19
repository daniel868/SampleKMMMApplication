package com.example.kmmapplication.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

const val RECIPE_IMAGE_HEIGHT = 260

@Composable
fun RecipeImage(
    url: String,
    contentDescription: String
) {
    val painter = rememberCoilPainter(request = url)
    Box {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
            contentScale = ContentScale.Crop
        )
    }

    when (painter.loadState) {
        is ImageLoadState.Error -> {
            //do smth for error
        }
        is ImageLoadState.Success -> {
            //Do smth
        }
        is ImageLoadState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(RECIPE_IMAGE_HEIGHT.dp)
            ) {
                // empty for white background
            }
        }
    }
}