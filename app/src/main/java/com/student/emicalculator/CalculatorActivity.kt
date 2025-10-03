package com.student.emicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

/**
 * CalculatorActivity - Main calculator screen
 * Handles user input for loan details, income, and expenses
 * Calculates EMI using the standard formula and passes results to ResultActivity
 */
class CalculatorActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var etLoanAmount: EditText
    private lateinit var etInterestRate: EditText
    private lateinit var etLoanTenure: EditText
    private lateinit var etMonthlyIncome: EditText
    private lateinit var etMonthlyExpenses: EditText
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // Enable back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize UI components
        initializeViews()

        // Set up calculate button click listener
        btnCalculate.setOnClickListener {
            calculateAndNavigate()
        }
    }

    /**
     * Initialize all view components
     */
    private fun initializeViews() {
        etLoanAmount = findViewById(R.id.etLoanAmount)
        etInterestRate = findViewById(R.id.etInterestRate)
        etLoanTenure = findViewById(R.id.etLoanTenure)
        etMonthlyIncome = findViewById(R.id.etMonthlyIncome)
        etMonthlyExpenses = findViewById(R.id.etMonthlyExpenses)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    /**
     * Validate inputs, calculate EMI, and navigate to ResultActivity
     */
    private fun calculateAndNavigate() {
        // Get input values as strings
        val loanAmountStr = etLoanAmount.text.toString().trim()
        val interestRateStr = etInterestRate.text.toString().trim()
        val tenureStr = etLoanTenure.text.toString().trim()
        val incomeStr = etMonthlyIncome.text.toString().trim()
        val expensesStr = etMonthlyExpenses.text.toString().trim()

        // Validate that all fields are filled
        if (loanAmountStr.isEmpty() || interestRateStr.isEmpty() || 
            tenureStr.isEmpty() || incomeStr.isEmpty() || expensesStr.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_field, Toast.LENGTH_SHORT).show()
            return
        }

        try {
            // Convert strings to numbers
            val loanAmount = loanAmountStr.toDouble()
            val annualInterestRate = interestRateStr.toDouble()
            val tenureYears = tenureStr.toInt()
            val monthlyIncome = incomeStr.toDouble()
            val monthlyExpenses = expensesStr.toDouble()

            // Validate positive values
            if (loanAmount <= 0 || annualInterestRate < 0 || tenureYears <= 0 || 
                monthlyIncome <= 0 || monthlyExpenses < 0) {
                Toast.makeText(this, "Please enter valid positive values", Toast.LENGTH_SHORT).show()
                return
            }

            // Calculate monthly EMI
            val monthlyEMI = calculateEMI(loanAmount, annualInterestRate, tenureYears)

            // Create intent with calculated values
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("MONTHLY_EMI", monthlyEMI)
                putExtra("MONTHLY_INCOME", monthlyIncome)
                putExtra("MONTHLY_EXPENSES", monthlyExpenses)
            }

            // Start ResultActivity
            startActivity(intent)

        } catch (e: NumberFormatException) {
            Toast.makeText(this, R.string.error_invalid_input, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Calculate EMI using the standard formula:
     * EMI = P × r × (1 + r)^n / ((1 + r)^n - 1)
     * Where:
     * P = Principal loan amount
     * r = Monthly interest rate (annual rate / 12 / 100)
     * n = Total number of months (years × 12)
     */
    private fun calculateEMI(principal: Double, annualRate: Double, years: Int): Double {
        // Convert annual interest rate to monthly rate
        val monthlyRate = annualRate / 12 / 100
        
        // Calculate total number of months
        val numberOfMonths = years * 12

        // Handle zero interest rate case
        if (monthlyRate == 0.0) {
            return principal / numberOfMonths
        }

        // Apply EMI formula
        val emi = principal * monthlyRate * (1 + monthlyRate).pow(numberOfMonths) / 
                  ((1 + monthlyRate).pow(numberOfMonths) - 1)

        return emi
    }

    /**
     * Handle back button press in action bar
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}