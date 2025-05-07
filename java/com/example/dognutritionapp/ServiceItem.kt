package com.example.dognutritionapp

data class ServiceItem(
    val imageResId: Int,      // Resource ID for the image (e.g., R.drawable.image_dog_walking)
    val name: String,         // Name of the service (e.g., "Dog Walking")
    val provider: String,     // Provider name (e.g., "Caesar Milan")
    val rating: Float,        // Rating (e.g., 4.9f)
    val price: Int            // Price (e.g., 500)
)
