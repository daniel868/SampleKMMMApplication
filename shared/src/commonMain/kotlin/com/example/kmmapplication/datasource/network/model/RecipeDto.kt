package com.example.kmmapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("pk")
    val pk: Int,

    @SerialName("title")
    val title: String,

    @SerialName("publisher")
    val publisher: String,

    @SerialName("featured_image")
    val featuredImage: String,

    @SerialName("rating")
    var rating: Int = 0,

    @SerialName("source_url")
    var sourceUrl: String,

    @SerialName("ingredients")
    var ingredients: List<String> = emptyList(),

    @SerialName("long_date_added")
    var longDateAdded: Long,

    @SerialName("long_date_updated")
    var longDateUpdated: Long,
)
