// ExtraFragment.kt
package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExtraFragment : Fragment(R.layout.fragment_extra) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the back button and set up the click listener
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Setup RecyclerView with ProductAdapter
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_products)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ProductAdapter(getProductList())
    }

    private fun getProductList(): List<Product> {
        return listOf(
            Product(R.drawable.ic_product_1, "Dog Collar", "Durable and comfortable", "LKR 300"),
            Product(R.drawable.ic_product_2, "Chew Toy", "For playful pups", "LKR 150"),
            Product(R.drawable.ic_product_3, "Nail Cutter", "Keep nails in check", "LKR 200"),
            Product(R.drawable.ic_product_4, "Harness", "Comfortable for walks", "LKR 500"),
            Product(R.drawable.ic_product_5, "Dog Leash", "Strong and stylish", "LKR 250"),
            Product(R.drawable.ic_product_6, "Pet Tags", "Customizable name tags", "LKR 100"),
            Product(R.drawable.ic_product_7, "Water Bottle", "Portable hydration", "LKR 180"),
            Product(R.drawable.ic_product_8, "Dog Bed", "Cozy and warm", "LKR 600"),
            Product(R.drawable.ic_product_9, "Grooming Brush", "Soft bristles for comfort", "LKR 120"),
            Product(R.drawable.ic_product_10, "Dog Feeding Plate", "Perfect for mealtime", "LKR 250")
        )
    }
}
