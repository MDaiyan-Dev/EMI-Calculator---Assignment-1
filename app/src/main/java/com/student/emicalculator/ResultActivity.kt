package com.student.emicalculator

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.text.NumberFormat
import java.util.Locale

/**
 * ResultActivity - Displays the budget calculation results
 * Shows EMI, income, expenses, and the final budget balance
 * Indicates whether the user has savings or a deficit
 */
class ResultActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var tvMonthlyEMI: TextView
    private lateinit var tvTotalIncome: TextView
    private lateinit var tvTotalExpenses: TextView
    private lateinit var tvBalanceAmount: TextView
    private lateinit var tvBalanceStatus: TextView
    private lateinit var balanceCard: CardView
    private lateinit var btnRecalculate: Button
    private lateinit var btnBackHome: Button

    // Currency formatter for displaying amounts
    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Enable back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize UI components
        initializeViews()

        // Retrieve data from intent
        val monthlyEMI = intent.getDoubleExtra("MONTHLY_EMI", 0.0)
        val monthlyIncome = intent.getDoubleExtra("MONTHLY_INCOME", 0.0)
        val monthlyExpenses = intent.getDoubleExtra("MONTHLY_EXPENSES", 0.0)

        // Calculate budget balance
        val budgetBalance = monthlyIncome - (monthlyEMI + monthlyExpenses)

        // Display results
        displayResults(monthlyEMI, monthlyIncome, monthlyExpenses, budgetBalance)

        // Set up button listeners
        setupButtonListeners()
    }

    /**
     * Initialize all view components
     */
    private fun initializeViews() {
        tvMonthlyEMI = findViewById(R.id.tvMonthlyEMI)
        tvTotalIncome = findViewById(R.id.tvTotalIncome)
        tvTotalExpenses = findViewById(R.id.tvTotalExpenses)
        tvBalanceAmount = findViewById(R.id.tvBalanceAmount)
        tvBalanceStatus = findViewById(R.id.tvBalanceStatus)
        balanceCard = findViewById(R.id.balanceCard)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        btnBackHome = findViewById(R.id.btnBackHome)
    }

    /**
     * Display all calculated results in the UI
     */
    private fun displayResults(emi: Double, income: Double, expenses: Double, balance: Double) {
        // Display EMI
        tvMonthlyEMI.text = currencyFormatter.format(emi)

        // Display income
        tvTotalIncome.text = currencyFormatter.format(income)

        // Display total expenses (EMI + other expenses)
        val totalExpenses = emi + expenses
        tvTotalExpenses.text = currencyFormatter.format(totalExpenses)

        // Display balance and update UI based on savings or deficit
        tvBalanceAmount.text = currencyFormatter.format(Math.abs(balance))

        if (balance >= 0) {
            // User has savings
            tvBalanceStatus.text = getString(R.string.savings_text)
            tvBalanceAmount.setTextColor(getColor(R.color.success))
            balanceCard.setCardBackgroundColor(getColor(R.color.primary_light))
        } else {
            // User has deficit
            tvBalanceStatus.text = getString(R.string.deficit_text)
            tvBalanceAmount.setTextColor(getColor(R.color.error))
            balanceCard.setCardBackgroundColor(Color.parseColor("#FFEBEE"))
        }
    }

    /**
     * Set up click listeners for action buttons
     */
    private fun setupButtonListeners() {
        // Recalculate button - go back to CalculatorActivity
        btnRecalculate.setOnClickListener {
            finish() // Return to previous activity (CalculatorActivity)
        }

        // Back to Home button - return to MainActivity
        btnBackHome.setOnClickListener {
            // Create intent to go back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Clear the activity stack so back button won't go through all activities
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    /**
     * Handle back button press in action bar
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}