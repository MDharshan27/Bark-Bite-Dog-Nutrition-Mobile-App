package com.example.dognutritionapp

data class PaymentSummary(
    val subtotal: Double,
    val deliveryFee: Double,
    val total: Double,
    val discount: Double
)
