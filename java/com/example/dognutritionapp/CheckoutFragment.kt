package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class CheckoutFragment : Fragment(R.layout.fragment_checkout) {

    // List of Sri Lanka's 9 provinces
    private val provinces = listOf(
        "Central Province",
        "Eastern Province",
        "Northern Province",
        "Southern Province",
        "Western Province",
        "North Western Province",
        "North Central Province",
        "Uva Province",
        "Sabaragamuwa Province"
    )

    // List of Sri Lanka's 25 districts
    private val districts = listOf(
        "Ampara", "Anuradhapura", "Badulla", "Batticaloa", "Colombo", "Galle",
        "Gampaha", "Hambantota", "Jaffna", "Kalutara", "Kandy", "Kegalle", "Kilinochchi",
        "Kurunegala", "Mannar", "Matale", "Matara", "Monaragala", "Mullaitivu", "Nuwara Eliya",
        "Polonnaruwa", "Puttalam", "Ratnapura", "Trincomalee", "Vavuniya"
    )

    private lateinit var dbHelper: DatabaseHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelper = DatabaseHelper(requireContext())

        val nameEditText: EditText = view.findViewById(R.id.name_edit_text)
        val emailEditText: EditText = view.findViewById(R.id.email_edit_text)
        val addressEditText: EditText = view.findViewById(R.id.address_edit_text)
        val mobileEditText: EditText = view.findViewById(R.id.mobile_edit_text)
        val provinceSelector: MaterialAutoCompleteTextView =
            view.findViewById(R.id.province_selector)
        val districtSelector: MaterialAutoCompleteTextView =
            view.findViewById(R.id.district_selector)
        val backButton: View = view.findViewById(R.id.back_button)
        val paymentButton: View = view.findViewById(R.id.btn_payment)

        // Set up dropdowns for provinces and districts
        provinceSelector.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line, provinces
            )
        )
        districtSelector.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line, districts
            )
        )

        // Load saved data from SQLite database
        val savedData = dbHelper.getCheckoutData()
        nameEditText.setText(savedData["name"])
        emailEditText.setText(savedData["email"])
        addressEditText.setText(savedData["address"])
        mobileEditText.setText(savedData["mobile"])
        provinceSelector.setText(savedData["province"])
        districtSelector.setText(savedData["district"])

        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        paymentButton.setOnClickListener {
            val selectedProvince = provinceSelector.text.toString()
            val selectedDistrict = districtSelector.text.toString()
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val address = addressEditText.text.toString()
            val mobile = mobileEditText.text.toString()

            // Validate fields with Toast message
            if (selectedProvince.isEmpty() || selectedDistrict.isEmpty() || name.isEmpty() ||
                email.isEmpty() || address.isEmpty() || mobile.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all required fields",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Save the form data to SQLite database
            dbHelper.insertCheckoutData(
                name,
                email,
                address,
                mobile,
                selectedProvince,
                selectedDistrict
            )

            // Create a Bundle to pass data
            val bundle = Bundle().apply {
                putString("name", name)
                putString("email", email)
                putString("address", address)
                putString("mobile", mobile)
                putString("province", selectedProvince)
                putString("district", selectedDistrict)
            }

            // Navigate to PaymentFragment with the data
            val paymentFragment = PaymentFragment().apply {
                arguments = bundle
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, paymentFragment)
                .addToBackStack(null)
                .commit()
        }

    }
}