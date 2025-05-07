package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addAdapter: AddAdapter
    private lateinit var cartViewModel: CartViewModel
    private lateinit var subtotalTextView: TextView
    private lateinit var deliveryFeeTextView: TextView
    private lateinit var totalTextView: TextView

    private val deliveryFee = 800.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        subtotalTextView = view.findViewById(R.id.subtotal_text_view)
        deliveryFeeTextView = view.findViewById(R.id.delivery_fees_text_view)
        totalTextView = view.findViewById(R.id.total_text_view)

        addAdapter = AddAdapter(mutableListOf()) { updatedCartItems ->
            updateTotalPrice(updatedCartItems)
        }
        recyclerView.adapter = addAdapter

        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            addAdapter.updateData(cartItems)
            updateTotalPrice(cartItems)
        }

        view.findViewById<View>(R.id.btnCheckout).setOnClickListener {
            val subtotal = cartViewModel.cartItems.value?.sumOf { it.totalPrice() } ?: 0.0
            val total = subtotal + deliveryFee

            // Store the data in PaymentFragment's ViewModel
            cartViewModel.setPaymentSummary(subtotal, deliveryFee, total)

            // Navigate to CheckoutFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CheckoutFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.back_button).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun updateTotalPrice(cartItems: List<CartItem>?) {
        var subtotal = 0.0

        cartItems?.forEach { item ->
            subtotal += item.totalPrice()
        }

        val total = subtotal + deliveryFee

        subtotalTextView.text = "LKR %.2f".format(subtotal)
        deliveryFeeTextView.text = "LKR %.2f".format(deliveryFee)
        totalTextView.text = "Total: LKR %.2f".format(total)
    }
}
