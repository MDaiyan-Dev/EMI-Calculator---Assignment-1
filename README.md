EMI Calculator - Android Application
Course: SOFE 4640U - Mobile Application Development
Assignment: Assignment 1
Semester: Fall 2025
Instructor: Dr. Nasim Beigi-Mohammadi

Project Overview
This Android application helps users manage their financial balance by calculating mortgage EMI (Equated Monthly Installment), tracking monthly income and expenses, and displaying the resulting savings or deficit. The app addresses the common challenge of balancing mortgage payments with daily financial obligations.

Features
Core Functionality
1. EMI Calculator
Input Fields:
Loan Amount (Principal)
Interest Rate (Annual percentage)
Loan Tenure (Years)
Output: Calculated monthly EMI using the standard mortgage formula
Formula Used: EMI = P × r × (1 + r)^n / ((1 + r)^n - 1)
P = Principal loan amount
r = Monthly interest rate (annual rate / 12 / 100)
n = Total number of months (years × 12)
2. Income and Expense Tracking
Monthly income input
Monthly expenses input
Simple and intuitive data entry
3. Budget Balance Display
Calculates: Income - (EMI + Expenses)
Visual feedback:
Green display for positive balance (savings)
Red display for negative balance (deficit)
Clear summary of financial status
User Interface
The app features a clean, user-friendly interface with:

Material Design components
Card-based layouts for organized content grouping
Outlined text input fields with floating labels
Clear visual hierarchy
Responsive design that adapts to different screen sizes
Technical Implementation
Architecture
The application follows Android best practices with a simple three-activity architecture:

MainActivity - Welcome/Landing screen
CalculatorActivity - EMI calculation and data input
ResultActivity - Budget balance display
Key Technologies
Language: Kotlin
Minimum SDK: API 24 (Android 7.0)
Target SDK: API 34 (Android 14)
UI Framework: Material Design 3 Components
Build System: Gradle 8.4
Android Gradle Plugin: 8.3.0
Layouts Used
ConstraintLayout - For flexible, flat view hierarchies (MainActivity)
LinearLayout - For vertical stacking of components
ScrollView - To ensure content accessibility on smaller screens
CardView - For visually grouping related content
TextInputLayout - Material Design text input with floating labels
Views Used
TextView - For labels and displaying results
EditText (TextInputEditText) - For user input
Button (MaterialButton) - For navigation and actions
CardView - For content containers
Intents Used
Explicit Intents
MainActivity → CalculatorActivity
Triggered by "Get Started" button
Navigation to calculator screen
CalculatorActivity → ResultActivity
Triggered by "Calculate Budget" button
Passes data: EMI, income, expenses via Intent extras
ResultActivity → MainActivity
Triggered by "Back to Home" button
Uses FLAG_ACTIVITY_CLEAR_TOP to clear activity stack
ResultActivity → CalculatorActivity
Triggered by "Recalculate" button
Uses finish() to return to previous activity
Data Passing
Data is passed between activities using Intent extras:

kotlin
intent.putExtra("MONTHLY_EMI", monthlyEMI)
intent.putExtra("MONTHLY_INCOME", monthlyIncome)
intent.putExtra("MONTHLY_EXPENSES", monthlyExpenses)
Retrieved in the receiving activity:

kotlin
val monthlyEMI = intent.getDoubleExtra("MONTHLY_EMI", 0.0)
val monthlyIncome = intent.getDoubleExtra("MONTHLY_INCOME", 0.0)
val monthlyExpenses = intent.getDoubleExtra("MONTHLY_EXPENSES", 0.0)
Project Structure
app/src/main/
├── java/com/student/emicalculator/
│   ├── MainActivity.kt           # Landing screen
│   ├── CalculatorActivity.kt     # Input and EMI calculation
│   └── ResultActivity.kt         # Results display
├── res/
│   ├── layout/
│   │   ├── activity_main.xml          # Welcome screen layout
│   │   ├── activity_calculator.xml    # Calculator screen layout
│   │   └── activity_result.xml        # Results screen layout
│   ├── values/
│   │   ├── strings.xml                # String resources
│   │   └── colors.xml                 # Color palette
│   └── drawable/                      # (Empty - using emojis/text)
└── AndroidManifest.xml                # App configuration
Input Validation
The app implements comprehensive input validation:

Checks for empty fields
Validates numeric input
Ensures positive values for loan amount, tenure, income
Allows zero interest rate (edge case handling)
Displays user-friendly error messages via Toast notifications
Installation & Usage
Prerequisites
Android device or emulator running Android 7.0 (API 24) or higher
ADB (Android Debug Bridge) for installation via command line
Building from Source
Clone the repository
Navigate to project directory
Build the APK:
bash
   ./gradlew assembleDebug
Install on device:
bash
   ./gradlew installDebug
Using the App
Launch the app from your device
Tap "Get Started" on the welcome screen
Enter loan details:
Loan amount (e.g., 300000)
Interest rate (e.g., 5.5)
Loan tenure in years (e.g., 25)
Enter monthly finances:
Monthly income (e.g., 6000)
Monthly expenses (e.g., 2000)
Tap "Calculate Budget"
View results:
Monthly EMI amount
Total income
Total expenses (including EMI)
Budget balance (savings or deficit)
Navigate:
"Recalculate" to adjust inputs
"Back to Home" to return to main screen
Testing
Test Cases
Test Case 1: Standard Scenario

Loan Amount: $300,000
Interest Rate: 5.5%
Tenure: 25 years
Income: $6,000
Expenses: $2,000
Expected EMI: ~$1,844.52
Expected Balance: ~$2,155.48 (Savings - Green)
Test Case 2: Deficit Scenario

Loan Amount: $500,000
Interest Rate: 6.0%
Tenure: 20 years
Income: $4,000
Expenses: $2,000
Expected EMI: ~$3,582
Expected Balance: ~-$1,582 (Deficit - Red)
Test Case 3: Zero Interest

Loan Amount: $120,000
Interest Rate: 0%
Tenure: 10 years
Expected EMI: $1,000 (120,000 / 120 months)
Device Testing
Tested on physical Android device
Verified screen rotation handling
Confirmed smooth performance
Validated input/output accuracy
Best Practices Implemented
Android Development
Proper activity lifecycle management
Resource externalization (strings, colors)
Material Design guidelines
Responsive layouts
Proper intent usage and data passing
Back navigation support via parent activity declaration
Code Quality
Clear, descriptive variable and method names
Documented code with high-level explanations
Separation of concerns (UI logic vs calculation logic)
Input validation and error handling
Proper use of Kotlin features
Future Enhancements
Potential improvements for future versions:

Expense breakdown with multiple categories
Daily expense tracking
Data persistence (save calculations)
Charts and graphs for visualization
Payment schedule breakdown
Export results to PDF/CSV
Known Limitations
No data persistence (calculations are not saved)
Single EMI calculation at a time
Basic expense tracking (single total amount)
No multi-currency support
Resources & References
TD Mortgage Calculator - Reference for EMI calculation
Android Developer Documentation
Material Design Guidelines
License
This project is created for educational purposes as part of SOFE 4640U coursework.

Author
Student Project - Ontario Tech University
Fall 2025

Note: This application was developed as part of Assignment 1 for Mobile Application Development (SOFE 4640U). The implementation focuses on demonstrating understanding of Android layouts, views, and intents while providing a practical solution for personal finance management.

