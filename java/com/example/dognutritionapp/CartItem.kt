package com.example.dognutritionapp

// CartItem data class
data class CartItem(
    val name: String,
    val weight: String,
    val price: Double,  // Changed to Double for calculations
    val imageResId: Int,
    var quantity: Int = 1
) {
    // Method to calculate the total price for this item
    fun totalPrice(): Double {
        return price * quantity
    }
}
