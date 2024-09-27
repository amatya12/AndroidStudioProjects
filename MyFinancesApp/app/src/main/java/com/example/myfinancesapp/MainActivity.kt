package com.example.myfinancesapp

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var cdAccountNumber: EditText
    private lateinit var cdInitialBalance: EditText
    private lateinit var cdCurrentBalance: EditText
    private lateinit var cdInterestRate: EditText

    private lateinit var loanAccountNumber: EditText
    private lateinit var loanInitialBalance: EditText
    private lateinit var loanCurrentBalance: EditText
    private lateinit var loanPaymentAmount: EditText
    private lateinit var loanInterestRate: EditText

    private lateinit var checkingAccountNumber: EditText
    private lateinit var checkingCurrentBalance: EditText

    private lateinit var dbHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        // Initialize EditTexts
        cdAccountNumber = findViewById(R.id.cdAccountNumber)
        cdInitialBalance = findViewById(R.id.cdInitialBalance)
        cdCurrentBalance = findViewById(R.id.cdCurrentBalance)
        cdInterestRate = findViewById(R.id.cdInterestRate)

        loanAccountNumber = findViewById(R.id.loanAccountNumber)
        loanInitialBalance = findViewById(R.id.loanInitialBalance)
        loanCurrentBalance = findViewById(R.id.loanCurrentBalance)
        loanPaymentAmount = findViewById(R.id.loanPaymentAmount)
        loanInterestRate = findViewById(R.id.loanInterestRate)

        checkingAccountNumber = findViewById(R.id.checkingAccountNumber)
        checkingCurrentBalance = findViewById(R.id.checkingCurrentBalance)

        val accountTypeGroup: RadioGroup = findViewById(R.id.accountTypeGroup)
        val btnSave: Button = findViewById(R.id.btnSave)
        val btnCancel: Button = findViewById(R.id.btnCancel)

        accountTypeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioCD -> enableCDFields(true)
                R.id.radioLoan -> enableLoanFields(true)
                R.id.radioChecking -> enableCheckingFields(true)
            }
        }

        btnSave.setOnClickListener { saveData() }
        btnCancel.setOnClickListener { clearFields() }
    }





    private fun enableCDFields(enable: Boolean) {
        cdAccountNumber.isEnabled = enable
        cdInitialBalance.isEnabled = enable
        cdCurrentBalance.isEnabled = enable
        cdInterestRate.isEnabled = enable

        loanAccountNumber.isEnabled = false
        loanInitialBalance.isEnabled = false
        loanCurrentBalance.isEnabled = false
        loanPaymentAmount.isEnabled = false
        loanInterestRate.isEnabled = false

        checkingAccountNumber.isEnabled = false
        checkingCurrentBalance.isEnabled = false
    }

    private fun enableLoanFields(enable: Boolean) {
        cdAccountNumber.isEnabled = false
        cdInitialBalance.isEnabled = false
        cdCurrentBalance.isEnabled = false
        cdInterestRate.isEnabled = false

        loanAccountNumber.isEnabled = enable
        loanInitialBalance.isEnabled = enable
        loanCurrentBalance.isEnabled = enable
        loanPaymentAmount.isEnabled = enable
        loanInterestRate.isEnabled = enable

        checkingAccountNumber.isEnabled = false
        checkingCurrentBalance.isEnabled = false
    }

    private fun enableCheckingFields(enable: Boolean) {
        cdAccountNumber.isEnabled = false
        cdInitialBalance.isEnabled = false
        cdCurrentBalance.isEnabled = false
        cdInterestRate.isEnabled = false

        loanAccountNumber.isEnabled = false
        loanInitialBalance.isEnabled = false
        loanCurrentBalance.isEnabled = false
        loanPaymentAmount.isEnabled = false
        loanInterestRate.isEnabled = false

        checkingAccountNumber.isEnabled = enable
        checkingCurrentBalance.isEnabled = enable
    }

    private fun saveData() {
        val accountType = findViewById<RadioGroup>(R.id.accountTypeGroup).checkedRadioButtonId
        when (accountType) {
            R.id.radioCD -> {
                val cd = CD(
                    cdAccountNumber.text.toString(),
                    cdInitialBalance.text.toString().toDouble(),
                    cdCurrentBalance.text.toString().toDouble(),
                    cdInterestRate.text.toString().toDouble()
                )
                dbHelper.insertCD(cd)
            }
            R.id.radioLoan -> {
                val loan = Loan(
                    loanAccountNumber.text.toString(),
                    loanInitialBalance.text.toString().toDouble(),
                    loanCurrentBalance.text.toString().toDouble(),
                    loanPaymentAmount.text.toString().toDouble(),
                    loanInterestRate.text.toString().toDouble()
                )
                dbHelper.insertLoan(loan)
            }
            R.id.radioChecking -> {
                val checkingAccount = CheckingAccount(
                    checkingAccountNumber.text.toString(),
                    checkingCurrentBalance.text.toString().toDouble()
                )
                dbHelper.insertCheckingAccount(checkingAccount)
            }
        }
        Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show()
        clearFields()
    }

    private fun clearFields() {
        cdAccountNumber.text.clear()
        cdInitialBalance.text.clear()
        cdCurrentBalance.text.clear()
        cdInterestRate.text.clear()

        loanAccountNumber.text.clear()
        loanInitialBalance.text.clear()
        loanCurrentBalance.text.clear()
        loanPaymentAmount.text.clear()
        loanInterestRate.text.clear()

        checkingAccountNumber.text.clear()
        checkingCurrentBalance.text.clear()
    }
}
