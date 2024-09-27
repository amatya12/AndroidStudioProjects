package com.example.tipcalculator
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        resultTextView = findViewById(R.id.resultTextView)

        val button15: Button = findViewById(R.id.button15)
        val button18: Button = findViewById(R.id.button18)
        val button20: Button = findViewById(R.id.button20)

        button15.setOnClickListener {
            calculateTip(0.15)
        }

        button18.setOnClickListener {
            calculateTip(0.18)
        }

        button20.setOnClickListener {
            calculateTip(0.20)
        }
    }

    private fun calculateTip(percentage: Double) {
        val amountText = amountEditText.text.toString()
        if (amountText.isEmpty()) {
            resultTextView.text = "Tip: $0.00, Total Bill: $0.00"
            return
        }

        val amount = amountText.toDouble()
        val tip = amount * percentage
        val totalBill = amount + tip

        resultTextView.text = String.format("Tip: $%.2f, Total Bill: $%.2f", tip, totalBill)
    }
}
