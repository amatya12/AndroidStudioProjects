package com.example.contractorcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAX_RATE = 0.05 // 5% tax rate
    }

    private lateinit var laborCostEditText: EditText
    private lateinit var materialCostEditText: EditText
    private lateinit var subTotalTextView: TextView
    private lateinit var taxTextView: TextView
    private lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        laborCostEditText = findViewById(R.id.laborCostEditText)
        materialCostEditText = findViewById(R.id.materialCostEditText)
        subTotalTextView = findViewById(R.id.subTotalTextView)
        taxTextView = findViewById(R.id.taxTextView)
        totalTextView = findViewById(R.id.totalTextView)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculateCosts()
        }
    }

    private fun calculateCosts() {
        val laborCost = laborCostEditText.text.toString().toDoubleOrNull() ?: 0.0
        val materialCost = materialCostEditText.text.toString().toDoubleOrNull() ?: 0.0

        val subTotal = laborCost + materialCost
        val tax = subTotal * TAX_RATE
        val total = subTotal + tax

        subTotalTextView.text = String.format("Subtotal: $%.2f", subTotal)
        taxTextView.text = String.format("Tax: $%.2f", tax)
        totalTextView.text = String.format("Total: $%.2f", total)
    }
}
