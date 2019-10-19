package com.example.kebaborder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView vTextViewHello;
    private TextView vTextViewEditions;
    private CheckBox vCheckBoxSauce;
    private CheckBox vCheckBoxHot;
    private CheckBox vCheckBoxSeparately;
    private Spinner vSpinnerMain;
    private Spinner vSpinnerFree;

    private String name;
    private String password;

    private String food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        //Reading from previous activity.
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        //Taking of first product name.
        food = getString(R.string.shaurma);
        //Take info from layout.
        vTextViewHello = findViewById(R.id.textViewHi);
        String hello = String.format(getString(R.string.hello),name);
        vTextViewHello.setText(hello);
        vTextViewEditions = findViewById(R.id.textViewEditions);
        vCheckBoxSauce = findViewById(R.id.checkboxSauce);
        vCheckBoxHot = findViewById(R.id.checkboxHot);
        vCheckBoxSeparately = findViewById(R.id.checkboxSeparately);
        vSpinnerMain = findViewById(R.id.spinnerMainDelivery);
        vSpinnerFree = findViewById(R.id.spinnerFreeDelivery);



    }

    public void onClickChangeFood(View view) {
    }

    public void onClickSendOrder(View view) {
    }
}
