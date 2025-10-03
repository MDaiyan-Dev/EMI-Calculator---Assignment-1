package com.student.emicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity - Landing screen of the EMI Calculator app
 * This activity serves as the entry point and displays a welcome screen
 * with a button to navigate to the calculator screen
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Get Started button
        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)

        // Set click listener to navigate to CalculatorActivity
        btnGetStarted.setOnClickListener {
            // Create an explicit intent to start CalculatorActivity
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}