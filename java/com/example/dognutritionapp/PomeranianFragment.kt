package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class PomeranianFragment : Fragment(R.layout.fragment_pomeranian) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the back button and set up the click listener
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}