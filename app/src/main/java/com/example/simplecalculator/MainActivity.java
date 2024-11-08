package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String input = "";
    private double firstValue = 0, secondValue = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        findViewById(R.id.button0).setOnClickListener(this::appendNumber);
        findViewById(R.id.button1).setOnClickListener(this::appendNumber);
        findViewById(R.id.button2).setOnClickListener(this::appendNumber);
        findViewById(R.id.button3).setOnClickListener(this::appendNumber);
        findViewById(R.id.button4).setOnClickListener(this::appendNumber);
        findViewById(R.id.button5).setOnClickListener(this::appendNumber);
        findViewById(R.id.button6).setOnClickListener(this::appendNumber);
        findViewById(R.id.button7).setOnClickListener(this::appendNumber);
        findViewById(R.id.button8).setOnClickListener(this::appendNumber);
        findViewById(R.id.button9).setOnClickListener(this::appendNumber);
        findViewById(R.id.buttonDecimal).setOnClickListener(this::appendNumber);

        findViewById(R.id.buttonAdd).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(view -> setOperator("/"));

        findViewById(R.id.buttonEquals).setOnClickListener(view -> calculateResult());
        findViewById(R.id.buttonClear).setOnClickListener(view -> clearInput());
        findViewById(R.id.buttonAC).setOnClickListener(view -> clearInput());
    }

    private void appendNumber(View view) {
        Button button = (Button) view;
        input += button.getText().toString();
        resultTextView.setText(input);
    }

    private void setOperator(String op) {
        if (!input.isEmpty()) {
            firstValue = Double.parseDouble(input);
            input = "";
            operator = op;
        }
    }

    private void calculateResult() {
        if (!input.isEmpty() && !operator.isEmpty()) {
            secondValue = Double.parseDouble(input);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) result = firstValue / secondValue;
                    else {
                        resultTextView.setText("Error");
                        input = "";
                        operator = "";
                        return;
                    }
                    break;
            }

            resultTextView.setText(String.valueOf(result));
            input = String.valueOf(result);
            operator = "";
        }
    }

    private void clearInput() {
        input = "";
        firstValue = 0;
        secondValue = 0;
        operator = "";
        resultTextView.setText("0");
    }
}
