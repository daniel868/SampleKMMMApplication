package com.example.kmmapplication.android.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.kmmapplication.presentation.recipeList.FoodCategory
import com.example.kmmapplication.presentation.recipeList.FoodCategoryUtil
import com.example.kmmapplication.presentation.recipeList.RecipeListEvents

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    query: String,
    categories: List<FoodCategory>,
    selectedCategory: FoodCategory? = null,
    onSelectedCategoryChanged: (FoodCategory) -> Unit,
    onQueryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = onQueryChange,
                    label = {
                        Text(text = "Search..")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide()
                        }
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "SearchIcon")
                    },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
                )
            }
            LazyRow(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)) {
                items(categories) {
                    FoodCategoryChip(category = it.value,
                        isSelected = selectedCategory == it,
                        onSelectedCategoryChanged = {
                            FoodCategoryUtil().getFoodCategory(it)?.let { newCategory ->
                                onSelectedCategoryChanged(newCategory)
                            }
                        })
                }
            }
        }
    }
}