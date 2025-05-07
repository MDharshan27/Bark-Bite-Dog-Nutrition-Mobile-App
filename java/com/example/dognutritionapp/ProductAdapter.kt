// ProductAdapter.kt
package com.example.dognutritionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.image_product)
        val productTitle: TextView = itemView.findViewById(R.id.text_product_title)
        val productDescription: TextView = itemView.findViewById(R.id.text_product_description)
        val productPrice: TextView = itemView.findViewById(R.id.text_product_price)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_extras, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productImage.setImageResource(product.imageResId)
        holder.productTitle.text = product.name
        holder.productDescription.text = product.description
        holder.productPrice.text = product.price

        // Optional: Set up the click listener for "ADD" button if needed
        holder.addToCartButton.setOnClickListener {
            // Handle add-to-cart action here
        }
    }

    override fun getItemCount(): Int = productList.size
}
