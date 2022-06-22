package com.example.recipes.entity

import com.example.recipes.entity.Measure

data class IngredientExtended(
    val aisle: String?,
    val amount: Double?,
    val consitency: String?,
    val id: Long?,
    val image: String?,
    val measures: Map<String, Measure>?,
    val meta: List<String>?,
    val name: String?,
    val original: String?,
    val originalName: String?,
    val unit: String?
)