package com.example.dognutritionapp

data class FoodItem(
    val imageResId: Int,
    val name: String,
    val weight: String,
    val price: Double // Use Double for accurate calculations
)
