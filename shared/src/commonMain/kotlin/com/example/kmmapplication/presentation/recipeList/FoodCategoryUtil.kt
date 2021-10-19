package com.example.kmmapplication.presentation.recipeList

class FoodCategoryUtil {
    fun getAllFoodCategories(): List<FoodCategory> {
        return listOf(
            FoodCategory.ERROR,
            FoodCategory.CHICKEN,
            FoodCategory.BEEF,
            FoodCategory.SOUP,
            FoodCategory.DESSERT,
            FoodCategory.VEGETARIAN,
            FoodCategory.MILK,
            FoodCategory.VEGAN,
            FoodCategory.PIZZA,
            FoodCategory.DONUT
        )
    }

    fun getFoodCategory(value: String): FoodCategory? {
        val map = FoodCategory.values().associateBy { it.value }
        return map[value]
    }
}