package com.example.dognutritionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private var foodList: List<FoodItem>,
    private val onAddToCart: (CartItem) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.foodImage)
        val nameTextView: TextView = view.findViewById(R.id.foodName)
        val weightTextView: TextView = view.findViewById(R.id.foodWeight)
        val priceTextView: TextView = view.findViewById(R.id.foodPrice)
        val addToCartButton: Button = view.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]

        // Set the image and text fields for the food item
        holder.imageView.setImageResource(foodItem.imageResId)
        holder.nameTextView.text = foodItem.name
        holder.weightTextView.text = foodItem.weight
        holder.priceTextView.text = formatPrice(foodItem.price)

        // Handle the Add to Cart button click
        holder.addToCartButton.setOnClickListener {
            val cartItem = CartItem(
                name = foodItem.name,
                weight = foodItem.weight,
                price = foodItem.price,
                imageResId = foodItem.imageResId
            )
            onAddToCart(cartItem)
        }
    }

    // Helper function for price formatting
    private fun formatPrice(price: Double): String {
        return "LKR %.2f".format(price)
    }


    override fun getItemCount() = foodList.size

    // Method to update the food list dynamically
    fun updateData(newFoodList: List<FoodItem>) {
        foodList = newFoodList
        notifyDataSetChanged()
    }
}
