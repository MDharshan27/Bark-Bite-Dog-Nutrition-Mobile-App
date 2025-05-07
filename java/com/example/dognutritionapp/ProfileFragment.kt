package com.example.dognutritionapp

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var dobTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        nameTextView = view.findViewById(R.id.name)
        emailTextView = view.findViewById(R.id.email)
        phoneTextView = view.findViewById(R.id.phone_number)
        addressTextView = view.findViewById(R.id.address)
        dobTextView = view.findViewById(R.id.dob)

        // Initialize SharedPreferences to store profile data
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Load and display saved profile data
        loadProfileData()

        // Edit Profile button functionality
        val editProfileButton: Button = view.findViewById(R.id.btnEditProfile)
        editProfileButton.setOnClickListener { showEditProfileDialog() }

        // Logout button functionality
        val logoutButton: Button = view.findViewById(R.id.btnLogout)
        logoutButton.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        // Set up back button navigation
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun loadProfileData() {
        nameTextView.text = sharedPreferences.getString("profile_name", "Name")
        emailTextView.text = sharedPreferences.getString("profile_email", "Email address")
        phoneTextView.text = sharedPreferences.getString("profile_phone", "Phone Number")
        addressTextView.text = sharedPreferences.getString("profile_address", "Address")
        dobTextView.text = sharedPreferences.getString("profile_dob", "Date of Birth")
    }

    private fun showEditProfileDialog() {
        // Inflate the dialog layout
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit_profile, null)
        val editName = dialogView.findViewById<EditText>(R.id.edit_name)
        val editEmail = dialogView.findViewById<EditText>(R.id.edit_email)
        val editPhone = dialogView.findViewById<EditText>(R.id.edit_phone)
        val editAddress = dialogView.findViewById<EditText>(R.id.edit_address)
        val editDob = dialogView.findViewById<EditText>(R.id.edit_dob)

        // Pre-fill current data
        editName.setText(nameTextView.text)
        editEmail.setText(emailTextView.text)
        editPhone.setText(phoneTextView.text)
        editAddress.setText(addressTextView.text)
        editDob.setText(dobTextView.text)

        // Create and show the dialog
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        // Save button functionality
        dialogView.findViewById<Button>(R.id.save_button).setOnClickListener {
            val newName = editName.text.toString()
            val newEmail = editEmail.text.toString()
            val newPhone = editPhone.text.toString()
            val newAddress = editAddress.text.toString()
            val newDob = editDob.text.toString()

            // Save updated profile data in SharedPreferences
            with(sharedPreferences.edit()) {
                putString("profile_name", newName)
                putString("profile_email", newEmail)
                putString("profile_phone", newPhone)
                putString("profile_address", newAddress)
                putString("profile_dob", newDob)
                apply()
            }

            // Update TextViews
            loadProfileData()
            dialog.dismiss()
        }

        // Cancel button functionality
        dialogView.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
