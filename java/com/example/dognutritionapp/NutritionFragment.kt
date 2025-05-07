package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class NutritionFragment : Fragment(R.layout.fragment_nutrition) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the back button and set up the click listener
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Set up click listener to navigate to Pomeranian Fragment
        val pomeranianLayout: View = view.findViewById(R.id.frame_pomeranian)
        pomeranianLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, PomeranianFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to Husky Fragment
        val huskyLayout: View = view.findViewById(R.id.frame_husky)
        huskyLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HuskyFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to Shih Fragment
        val shihLayout: View = view.findViewById(R.id.frame_shih)
        shihLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, ShihFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to Labrador Fragment
        val labradorLayout: View = view.findViewById(R.id.frame_labrador)
        labradorLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, LabradorFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to Rottweiler Fragment
        val rottweilerLayout: View = view.findViewById(R.id.frame_rottweiler)
        rottweilerLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, RottweilerFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to Retriever Fragment
        val retrieverLayout: View = view.findViewById(R.id.frame_retriever)
        retrieverLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, RetrieverFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to Beagle Fragment
        val beagleLayout: View = view.findViewById(R.id.frame_beagle)
        beagleLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, BeagleFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set up click listener to navigate to Shepherd Fragment
        val shepherdLayout: View = view.findViewById(R.id.frame_shepherd)
        shepherdLayout.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, ShepherdFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}