package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    // Initialize views
    Button calculateEMIButton;
    TextInputEditText amortizationPeriodTextInput, interestRateTextInput, mortgagePrincipalAmountTextInput;
    TextView emiTextResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference every initialized view by the ID given on the activity_main.xml file
        amortizationPeriodTextInput = (TextInputEditText) findViewById(R.id.AmortizationPeriodTextInput);
        calculateEMIButton = (Button) findViewById(R.id.CalculateButton);
        emiTextResult = (TextView) findViewById(R.id.EMIResults);
        interestRateTextInput = (TextInputEditText) findViewById(R.id.InterestRateTextInput);
        mortgagePrincipalAmountTextInput = (TextInputEditText) findViewById(R.id.MortgagePrincipalAmountTextInput);

        // Event fires when the calculate button is tapped that executes the displayEMIResult() function
        calculateEMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayEMIResult();
            }
        });

    }


    // Function that calculates is responsible for calculating the EMI result
    private float calculateEMIResult() {

        // Initialize variables needed to calculate the EMI result
        float amortizationPeriod, calculatedEMIResult, interestRate, mortgagePrincipalAmount;

        // Retrieve the numbers in the number box inputs and convert them to strings
        try { // Try and calculate the EMI with the inputs
            amortizationPeriod = Float.parseFloat(amortizationPeriodTextInput.getText().toString()) * 12; // Multiply by 12 months
            interestRate = Float.parseFloat(interestRateTextInput.getText().toString()) / 12 / 100; // Divide by number of months and then by 100 to obtain decimal percentage
            mortgagePrincipalAmount = Float.parseFloat(mortgagePrincipalAmountTextInput.getText().toString());

            // Calculate the EMI using the EMI formula
            calculatedEMIResult = (float) ((mortgagePrincipalAmount * interestRate * Math.pow(1 + interestRate, amortizationPeriod)) / (Math.pow(1 + interestRate, amortizationPeriod) - 1));

        } catch (Exception exception) { // Catch errors due to invalid inputs

            calculatedEMIResult = (float) -1; // Set result to error code
        }
        // Return the EMI result
        return calculatedEMIResult;

    }

    // Function that calls the calculate EMI function and displays its result
    private void displayEMIResult() {

        float calculatedEMIResult = calculateEMIResult(); // Retrieve EMI results
        String resultMessage; // Initialize resultant text message

        if (calculatedEMIResult == (float) -1) { // If the calculateEMIResult function returned the error code

            resultMessage = getString(R.string.calculation_result_error_text); // Retrieve the default error string message in values

        } else {

            resultMessage = getString(R.string.calculation_result_text, calculatedEMIResult); // Retrieve default string message in values and insert EMI number in it
        }

        emiTextResult.setText(resultMessage); // Display the resultant message to the screen

    }

}