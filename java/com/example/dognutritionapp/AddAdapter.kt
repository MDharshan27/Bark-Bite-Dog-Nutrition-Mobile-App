package com.example.dognutritionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddAdapter(
    private var cartItems: MutableList<CartItem>,
    private val updateTotalPrice: (List<CartItem>) -> Unit // Callback to update total price
) : RecyclerView.Adapter<AddAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]

        holder.itemName.text = cartItem.name
        holder.itemPrice.text = "LKR %.2f".format(cartItem.totalPrice()) // Display total price for the item
        holder.itemImage.setImageResource(cartItem.imageResId)
        holder.quantityText.text = cartItem.quantity.toString()

        holder.removeButton.setOnClickListener {
            // Remove the item from the list and notify the adapter
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size) // Update remaining items' positions
            updateTotalPrice(cartItems) // Update total price after removal
        }

        // Increase quantity
        holder.plusButton.setOnClickListener {
            cartItem.quantity++
            holder.quantityText.text = cartItem.quantity.toString()
            holder.itemPrice.text = "LKR %.2f".format(cartItem.totalPrice()) // Update displayed total price for the item
            updateTotalPrice(cartItems) // Update total price after quantity change
        }

        // Decrease quantity
        holder.minusButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                holder.quantityText.text = cartItem.quantity.toString()
                holder.itemPrice.text = "LKR %.2f".format(cartItem.totalPrice()) // Update displayed total price for the item
                updateTotalPrice(cartItems) // Update total price after quantity change
            }
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateData(newCartItems: List<CartItem>) {
        cartItems.clear()
        cartItems.addAll(newCartItems)
        notifyDataSetChanged()
    }

    // ViewHolder class
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.food_name)
        val itemPrice: TextView = itemView.findViewById(R.id.food_price)
        val itemImage: ImageView = itemView.findViewById(R.id.food_image)
        val removeButton: View = itemView.findViewById(R.id.delete_button)
        val quantityText: TextView = itemView.findViewById(R.id.quantity_text)
        val plusButton: ImageView = itemView.findViewById(R.id.btn_plus)
        val minusButton: ImageView = itemView.findViewById(R.id.btn_minus)
    }
}
