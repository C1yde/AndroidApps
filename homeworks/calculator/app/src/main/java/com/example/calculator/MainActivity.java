package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        ViewGroup rootView = (ViewGroup)((ViewGroup)this.findViewById(android.R.id.content)).getChildAt(0);
        ArrayList<View> numberButtons = getViewsByTag(rootView, "number");

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonText = b.getText().toString();
                long number;
                try{
                    number = Long.parseLong(main_text.getText() + buttonText);
                }
                catch (Exception e)
                {
                    number = Long.parseLong(main_text.getText().toString());
                }

                main_text.setText(String.format(Locale.getDefault(), "%d", number));
            }
        };
        for (View button: numberButtons) {
            button.setOnClickListener(oclBtnOk);
        }
    }

    private static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
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
}
