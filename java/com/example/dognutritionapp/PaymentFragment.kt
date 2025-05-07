package com.example.dognutritionapp

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog

class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private lateinit var cartViewModel: CartViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize CartViewModel
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        // Back Button Logic
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Observe and display payment summary from CartViewModel
        cartViewModel.paymentSummary.observe(viewLifecycleOwner) { paymentSummary ->
            paymentSummary?.let {
                // Display the subtotal
                view.findViewById<TextView>(R.id.subtotal).text = "Subtotal: LKR %.2f".format(it.subtotal)
                // Display the delivery fee
                view.findViewById<TextView>(R.id.delivery_fee).text = "Delivery Fee: LKR %.2f".format(it.deliveryFee)
                // Display the discount
                view.findViewById<TextView>(R.id.discount).text = "Discount: LKR %.2f".format(it.discount)
                // Display the total after applying the discount
                view.findViewById<TextView>(R.id.total).text = "Total: LKR %.2f".format(it.total)
            }
        }

        // Retrieve and display user details from arguments
        arguments?.let { bundle ->
            val name = bundle.getString("name", "")
            val email = bundle.getString("email", "")
            val address = bundle.getString("address", "")
            val mobile = bundle.getString("mobile", "")
            val province = bundle.getString("province", "")
            val district = bundle.getString("district", "")

            view.findViewById<TextView>(R.id.name_text_view).text = "Name: $name"
            view.findViewById<TextView>(R.id.email_text_view).text = "Email: $email"
            view.findViewById<TextView>(R.id.address_text_view).text = "Address: $address"
            view.findViewById<TextView>(R.id.mobile_text_view).text = "Mobile: $mobile"
            view.findViewById<TextView>(R.id.province_text_view).text = "Province: $province"
            view.findViewById<TextView>(R.id.district_text_view).text = "District: $district"
        }

        // Payment method selection and validation
        val radioGroup: RadioGroup = view.findViewById(R.id.payment_method_group)
        val cardForm: LinearLayout = view.findViewById(R.id.card_payment_form)
        val checkoutButton: Button = view.findViewById(R.id.btn_checkout)

        // Default States
        cardForm.visibility = View.GONE // Hide card form initially
        checkoutButton.text = "Checkout"

        // Set default selected payment method
        radioGroup.check(R.id.rb_cash_on_delivery)

        // Radio Group Listener
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_cash_on_delivery -> {
                    cardForm.visibility = View.GONE // Hide card form
                    checkoutButton.text = "Checkout"
                }
                R.id.rb_card_payment -> {
                    cardForm.visibility = View.VISIBLE // Show card form
                    checkoutButton.text = "Pay Now"
                }
            }
        }

        // Setup expiration date input
        setupExpirationDateInput(view)

        // Checkout Button Listener
        checkoutButton.setOnClickListener {
            val message = when (radioGroup.checkedRadioButtonId) {
                R.id.rb_cash_on_delivery -> "Order Placed Successfully!"
                R.id.rb_card_payment -> {
                    if (validateCardDetails(view)) {
                        "Payment Successful! Order Placed Successfully!"
                    } else {
                        return@setOnClickListener // Stop further execution if validation fails
                    }
                }
                else -> "Please select a payment method."
            }

            // Show Bottom Sheet Dialog with confirmation message
            showConfirmationDialog(message)
        }
    }

    /**
     * Sets up the expiration date input field with a TextWatcher to automatically add "/".
     */
    private fun setupExpirationDateInput(view: View) {
        val expirationDate: EditText = view.findViewById(R.id.expiration_date)
        expirationDate.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_NORMAL

        expirationDate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val currentText = expirationDate.text.toString()
                if (currentText.length == 2 && !currentText.contains("/")) {
                    expirationDate.setText("$currentText/")
                    expirationDate.setSelection(expirationDate.length()) // Move cursor to the end
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    /**
     * Validates card details when Card Payment is selected.
     */
    private fun validateCardDetails(view: View): Boolean {
        val cardholderName: EditText = view.findViewById(R.id.cardholder_name)
        val cardNumber: EditText = view.findViewById(R.id.card_number)
        val expirationDate: EditText = view.findViewById(R.id.expiration_date)
        val cvv: EditText = view.findViewById(R.id.cvv)

        when {
            cardholderName.text.isNullOrEmpty() -> {
                cardholderName.error = "Cardholder name is required"
                return false
            }
            cardNumber.text.isNullOrEmpty() || cardNumber.text.length != 16 -> {
                cardNumber.error = "Enter a valid 16-digit card number"
                return false
            }
            expirationDate.text.isNullOrEmpty() || !expirationDate.text.matches(Regex("\\d{2}/\\d{4}")) -> {
                expirationDate.error = "Enter a valid expiration date (MM/YYYY)"
                return false
            }
            cvv.text.isNullOrEmpty() || cvv.text.length != 3 -> {
                cvv.error = "Enter a valid 3-digit CVV"
                return false
            }
        }

        return true // Validation successful
    }

    /**
     * Displays a confirmation message using a Bottom Sheet Dialog.
     */
    private fun showConfirmationDialog(message: String) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_confirmation, null)

        val messageTextView: TextView = dialogView.findViewById(R.id.tv_confirmation_message)
        val subtextTextView: TextView = dialogView.findViewById(R.id.tv_confirmation_subtext)
        val confirmationIcon: ImageView = dialogView.findViewById(R.id.iv_confirmation_icon)
        val closeButton: Button = dialogView.findViewById(R.id.btn_close)

        messageTextView.text = message

        when (message) {
            "Order Placed Successfully!" -> {
                subtextTextView.text = "Thank you for your order. Check the order tracking page for details."
                confirmationIcon.setImageResource(R.drawable.ic_checkmark)
            }
            "Payment Successful! Order Placed Successfully!" -> {
                subtextTextView.text = "Your payment was successful. Thank you!"
                confirmationIcon.setImageResource(R.drawable.ic_checkmark)
            }
            "Please select a payment method." -> {
                subtextTextView.text = "Error: You must select a payment method."
                confirmationIcon.setImageResource(R.drawable.ic_error)
            }
            else -> {
                subtextTextView.text = "An unexpected error occurred. Please try again."
                confirmationIcon.setImageResource(R.drawable.ic_error)
            }
        }

        closeButton.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(dialogView)
        bottomSheetDialog.show()
    }
}
