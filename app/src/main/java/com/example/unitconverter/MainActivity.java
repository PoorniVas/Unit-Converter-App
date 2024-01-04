package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerFrom, spinnerTo;
    private Button btnConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValue = findViewById(R.id.editTextValue);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnConvert = findViewById(R.id.btnConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.length_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert();
            }
        });
    }

    private void convert() {
        // Get user input
        double inputValue = Double.parseDouble(editTextValue.getText().toString());

        // Get selected units
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        // Perform unit conversion
        double result = performConversion(inputValue, fromUnit, toUnit);

        // Display the result
        String resultText = String.format("%.2f %s = %.2f %s", inputValue, fromUnit, result, toUnit);
        textViewResult.setText(resultText);
    }

    private double performConversion(double value, String fromUnit, String toUnit) {
        // Implement your conversion logic here
        // Use appropriate conversion formulas based on the selected units
        if (fromUnit.equals("Meters") && toUnit.equals("Centimeters")) {
            return value * 100;
        } else if (fromUnit.equals("Meters") && toUnit.equals("Millimeters")) {
            return value * 1000;
        } else if (fromUnit.equals("Centimeters") && toUnit.equals("Meters")) {
            return value / 100;
        } else if (fromUnit.equals("Centimeters") && toUnit.equals("Millimeters")) {
            return value * 10;
        } else if (fromUnit.equals("Millimeters") && toUnit.equals("Meters")) {
            return value / 1000;
        } else if (fromUnit.equals("Millimeters") && toUnit.equals("Centimeters")) {
            return value / 10;
        } else {
            // Conversion between the same units
            return value;
        }
    }
}
