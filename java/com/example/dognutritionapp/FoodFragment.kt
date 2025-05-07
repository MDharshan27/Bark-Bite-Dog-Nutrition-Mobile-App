package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class FoodFragment : Fragment(R.layout.fragment_food) {

    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var cartViewModel: CartViewModel // ViewModel to share cart data

    // Sample data for each category
    private val foodData = mapOf(
        "Dry Food" to listOf(
            FoodItem(R.drawable.dry_food_1, "Royal Canin Size Health Nutrition Mini Adult", "1 kg", 5500.0),
            FoodItem(R.drawable.dry_food_2, "Hill's Science Diet Adult Chicken Recipe", "1 kg", 6000.0),
            FoodItem(R.drawable.dry_food_3, "Purina Pro Plan Savor Adult Chicken & Rice", "1 kg", 5800.0),
            FoodItem(R.drawable.dry_food_4, "Blue Buffalo Life Protection Formula", "1 kg", 6200.0),
            FoodItem(R.drawable.dry_food_5, "Orijen Original Dry Dog Food", "1 kg", 8000.0),
            FoodItem(R.drawable.dry_food_6, "Taste of the Wild High Prairie Canine Recipe", "1 kg", 6500.0),
            FoodItem(R.drawable.dry_food_7, "Merrick Grain-Free Texas Beef & Sweet Potato", "1 kg", 6800.0),
            FoodItem(R.drawable.dry_food_8, "Canidae Pure Limited Ingredient Grain-Free", "1 kg", 6400.0),
            FoodItem(R.drawable.dry_food_9, "Nutrish Zero Grain Natural Dry Dog Food", "1 kg", 5200.0),
            FoodItem(R.drawable.dry_food_10, "Wellness CORE Grain-Free Original", "1 kg", 6700.0)
        ),
        "Wet Food" to listOf(
            FoodItem(R.drawable.wet_food_1, "Hill's Science Diet Adult Wet Dog Food", "1 kg", 4000.0),
            FoodItem(R.drawable.wet_food_2, "Royal Canin Size Health Nutrition Wet Dog Food", "1 kg", 4200.0),
            FoodItem(R.drawable.wet_food_3, "Blue Buffalo Homestyle Recipe", "1 kg", 3800.0),
            FoodItem(R.drawable.wet_food_4, "Purina Pro Plan Savor Adult Wet Dog Food", "1 kg", 4500.0),
            FoodItem(R.drawable.wet_food_5, "Merrick Grain-Free Texas Beef & Sweet Potato Wet Food", "1 kg", 4800.0),
            FoodItem(R.drawable.wet_food_6, "Nature's Logic Canned Dog Food", "1 kg", 3900.0),
            FoodItem(R.drawable.wet_food_7, "Wellness CORE Grain-Free Wet Dog Food", "1 kg", 4400.0),
            FoodItem(R.drawable.wet_food_8, "Rachael Ray Nutrish Wet Dog Food", "1 kg", 3700.0),
            FoodItem(R.drawable.wet_food_9, "Victor Hi-Pro Plus Canned Dog Food", "1 kg", 4100.0),
            FoodItem(R.drawable.wet_food_10, "Instinct Original Grain-Free Wet Dog Food", "1 kg", 4600.0)
        ),
        "Raw Food" to listOf(
            FoodItem(R.drawable.raw_food_1, "Instinct Raw Boost Mixers Freeze-Dried Raw", "1 kg", 6000.0),
            FoodItem(R.drawable.raw_food_2, "Stella & Chewy's Freeze-Dried Raw Dinner Patties", "1 kg", 7000.0),
            FoodItem(R.drawable.raw_food_3, "Primal Pet Foods Raw Frozen Dog Food", "1 kg", 7500.0),
            FoodItem(R.drawable.raw_food_4, "Nutriment Raw Dog Food", "1 kg", 5500.0),
            FoodItem(R.drawable.raw_food_5, "Natures Menu Raw Dog Food", "1 kg", 5800.0),
            FoodItem(R.drawable.raw_food_6, "Tucker's Raw Frozen Dog Food", "1 kg", 6200.0),
            FoodItem(R.drawable.raw_food_7, "Pawtree Raw Food Diet", "1 kg", 5700.0),
            FoodItem(R.drawable.raw_food_8, "Merrick Raw Infused Kibble", "1 kg", 6800.0),
            FoodItem(R.drawable.raw_food_9, "Open Farm Freeze-Dried Raw Dog Food", "1 kg", 7200.0),
            FoodItem(R.drawable.raw_food_10, "Sojos Complete Dehydrated Dog Food", "1 kg", 5600.0)
        ),
        "Semi-Moist Food" to listOf(
            FoodItem(R.drawable.semi_moist_food_1, "Purina Moist & Meaty Dog Food", "1 kg", 3200.0),
            FoodItem(R.drawable.semi_moist_food_2, "Pet Greens Semi-Moist Dog Treats", "1 kg", 2500.0),
            FoodItem(R.drawable.semi_moist_food_3, "Merrick Texas Beef & Sweet Potato Treats", "1 kg", 3000.0),
            FoodItem(R.drawable.semi_moist_food_4, "Kibbles 'n Bits Original", "1 kg", 3500.0),
            FoodItem(R.drawable.semi_moist_food_5, "Buddy Biscuits Soft & Chewy Dog Treats", "1 kg", 2800.0),
            FoodItem(R.drawable.semi_moist_food_6, "Rachael Ray Nutrish Super Food Blend", "1 kg", 3200.0),
            FoodItem(R.drawable.semi_moist_food_7, "Alpo Variety Snaps", "1 kg", 2600.0),
            FoodItem(R.drawable.semi_moist_food_8, "Pedigree Soft Dog Food", "1 kg", 3300.0),
            FoodItem(R.drawable.semi_moist_food_9, "Hills Ideal Balance Semi-Moist", "1 kg", 3400.0),
            FoodItem(R.drawable.semi_moist_food_10, "Purina Beggin' Strips", "1 kg", 3000.0)
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        // Find the back button and set up the click listener
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Navigate back to HomeFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .addToBackStack(null) // Add this transaction to the back stack
                .commit()
        }

        // Initialize TabLayout
        tabLayout = view.findViewById(R.id.tabLayout)

        // Add tabs programmatically
        foodData.keys.forEach { category ->
            tabLayout.addTab(tabLayout.newTab().setText(category))
        }

        // Initialize RecyclerView and Adapter with dry food initially
        foodRecyclerView = view.findViewById(R.id.foodRecyclerView)
        foodAdapter = FoodAdapter(foodData["Dry Food"] ?: emptyList()) { cartItem ->
            addToCart(cartItem) // Handle adding to the cart
        }
        foodRecyclerView.layoutManager = LinearLayoutManager(context)
        foodRecyclerView.adapter = foodAdapter

        // Handle Tab Selection
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val selectedCategory = tab.text.toString()
                foodAdapter.updateData(foodData[selectedCategory] ?: emptyList())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // Method to handle adding items to the cart using ViewModel
    private fun addToCart(cartItem: CartItem) {
        cartViewModel.addItemToCart(cartItem) // Add to the ViewModel's cart
        Toast.makeText(requireContext(), "${cartItem.name} added to cart!", Toast.LENGTH_SHORT).show()
    }
}
