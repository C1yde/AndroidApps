package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView main_text = findViewById(R.id.main_text);
        final CalculationResult result = new CalculationResult();
        setNumberButtonsClick(main_text, result);
        setOperatorButtonsClick(main_text, result);
        setEquallyButtonClick(main_text, result);
        setClearButtonClick(main_text, result);
    }

    private void setClearButtonClick(final TextView main_text, final CalculationResult result)
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_text.setText(getString(R.string.zero));
                ResetResult(result);
            }
        };

        Button clearButton = findViewById(R.id.clear);
        clearButton.setOnClickListener(onClickListener);
    }

    private void setEquallyButtonClick(final TextView main_text, final CalculationResult result)
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.Operator != null)
                {
                    long calculation;
                    if (result.Operator.equals(getString(R.string.plus)))
                    {
                        calculation = Long.parseLong(result.FirstNumber)
                                + Long.parseLong(result.SecondNumber);
                    }
                    else
                    {
                        calculation = Long.parseLong(result.FirstNumber)
                                - Long.parseLong(result.SecondNumber);
                    }
                    main_text.setText(String.format(Locale.getDefault(), "%d", calculation));
                    ResetResult(result);
                }
            }
        };

        Button equallyButton = findViewById(R.id.equally);
        equallyButton.setOnClickListener(onClickListener);
    }

    private void setOperatorButtonsClick(final TextView main_text, final CalculationResult result)
    {
        ViewGroup rootView = (ViewGroup)((ViewGroup)this.findViewById(android.R.id.content)).getChildAt(0);
        ArrayList<View> operatorButtons = getViewsByTag(rootView, getString(R.string.operatorTag));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button)v;
                String buttonText = button.getText().toString();
                result.FirstNumber = main_text.getText().toString();
                result.Operator = buttonText;
            }
        };

        for (View button: operatorButtons) {
            button.setOnClickListener(onClickListener);
        }
    }

    private void setNumberButtonsClick(final TextView mainTextView, final CalculationResult result)
    {
        ViewGroup rootView = (ViewGroup)((ViewGroup)this.findViewById(android.R.id.content)).getChildAt(0);
        ArrayList<View> numberButtons = getViewsByTag(rootView, getString(R.string.numberTag));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberForEdit = result.FirstNumber;
                if (result.Operator != null) {
                    numberForEdit = result.SecondNumber;
                }

                long number;
                Button b = (Button)v;
                String buttonText = b.getText().toString();
                try{
                    number = Long.parseLong(numberForEdit + buttonText);
                }
                catch (Exception e)
                {
                    number = Long.parseLong(numberForEdit);
                }

                String resultNumber = String.format(Locale.getDefault(), "%d", number);
                mainTextView.setText(resultNumber);

                if (result.Operator == null) {
                    result.FirstNumber = resultNumber;
                }
                else{
                    result.SecondNumber = resultNumber;
                }
            }
        };

        for (View button: numberButtons) {
            button.setOnClickListener(onClickListener);
        }
    }

    private ArrayList<View> getViewsByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }
        }
        return views;
    }

    private void ResetResult(final CalculationResult result)
    {
        String zeroString = getString(R.string.zero);
        result.FirstNumber = zeroString;
        result.SecondNumber = zeroString;
        result.Operator = null;
    }

    private class CalculationResult {
        String FirstNumber = getString(R.string.zero);
        String SecondNumber = getString(R.string.zero);
        String Operator;
    }
}
