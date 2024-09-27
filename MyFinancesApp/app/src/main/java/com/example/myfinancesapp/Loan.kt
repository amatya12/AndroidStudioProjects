package com.example.myfinancesapp

data class Loan(
    val accountNumber: String,
    val initialBalance: Double,
    val currentBalance: Double,
    val paymentAmount: Double,
    val interestRate: Double
)