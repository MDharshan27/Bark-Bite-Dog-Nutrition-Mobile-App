package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedicineFragment : Fragment(R.layout.fragment_medicine) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vetRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_vets)
        vetRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val vetList = listOf(
            Veterinary(R.drawable.image_doctor_1, "Dr. Jenny Wilson", "Pawsnclaws Hospital", "2.7 km", "LKR 1000"),
            Veterinary(R.drawable.image_doctor_2, "Dr. Savannah Nguyen", "Vets Hospital", "3.1 km", "LKR 1500"),
            Veterinary(R.drawable.image_doctor_3, "Dr. Robert Fox", "Pups Care Vet", "4.0 km", "LKR 1200"),
            Veterinary(R.drawable.image_doctor_4, "Dr. Emily Carter", "Happy Paws Clinic", "1.5 km", "LKR 900"),
            Veterinary(R.drawable.image_doctor_5, "Dr. Olivia Lee", "City Pet Care", "2.3 km", "LKR 1200"),
            Veterinary(R.drawable.image_doctor_6, "Dr. Sophia Patel", "Pet Wellness Center", "3.8 km", "LKR 1300")
        )

        vetRecyclerView.adapter = VetAdapter(vetList)

        // Find the back button and set up the click listener
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Pop the back stack to return to HomeFragment
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
