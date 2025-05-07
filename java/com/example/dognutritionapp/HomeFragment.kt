package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var serviceRecyclerView: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serviceRecyclerView = view.findViewById(R.id.recycler_view)
        serviceRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample data
        val services = listOf(
            ServiceItem(R.drawable.image_dog_walking, "Dog Walking", "Caesar Milan", 4.9f, 500),
            ServiceItem(R.drawable.image_fluffy_bath, "Fluffy Bath", "Fluffy Spa", 5.0f, 900),
            ServiceItem(R.drawable.image_dog_training, "Dog Training", "Puppy School", 4.1f, 15000),
            ServiceItem(R.drawable.image_dog_walking, "Dog Walking", "Caesar Milan", 4.9f, 500),
            ServiceItem(R.drawable.image_fluffy_bath, "Fluffy Bath", "Fluffy Spa", 5.0f, 900),
            ServiceItem(R.drawable.image_dog_training, "Dog Training", "Puppy School", 4.1f, 15000)
        )

        serviceAdapter = ServiceAdapter(services)
        serviceRecyclerView.adapter = serviceAdapter

        // Set up click listener to navigate to NutritionFragment
        val nutritionLayout: View = view.findViewById(R.id.frame_nutrition)
        nutritionLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, NutritionFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to MedicineFragment
        val medicineLayout: View = view.findViewById(R.id.frame_medicine)
        medicineLayout.setOnClickListener {
            // Begin fragment transaction to replace HomeFragment with MedicineFragment
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, MedicineFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to HealthFragment
        val healthLayout: View = view.findViewById(R.id.frame_health)
        healthLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HealthFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to ExtraFragment
        val extraLayout: View = view.findViewById(R.id.frame_extra)
        extraLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, ExtraFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
