package com.example.dognutritionapp

data class Veterinary(
    val doctorImage: Int, // Resource ID for the image
    val name: String,
    val clinic: String,
    val distance: String, // Distance as a formatted string, e.g., "2.7 km"
    val fee: String       // Fee as a formatted string, e.g., "₹1000"
)
