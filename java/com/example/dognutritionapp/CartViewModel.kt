package com.example.dognutritionapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    val cartItems: MutableLiveData<MutableList<CartItem>> = MutableLiveData(mutableListOf())

    private val _paymentSummary = MutableLiveData<PaymentSummary>()
    val paymentSummary: LiveData<PaymentSummary> get() = _paymentSummary

    fun addItemToCart(item: CartItem) {
        val currentList = cartItems.value ?: mutableListOf()

        val existingItem = currentList.find { it.name == item.name && it.weight == item.weight }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            currentList.add(item)
        }
        cartItems.value = currentList
    }

    // Include discount logic here
    fun setPaymentSummary(subtotal: Double, deliveryFee: Double, total1: Double) {
        val discount = subtotal * 0.05 // Calculate 5% discount
        val total = subtotal + deliveryFee - discount
        _paymentSummary.value = PaymentSummary(subtotal, deliveryFee, total, discount)
    }
}
