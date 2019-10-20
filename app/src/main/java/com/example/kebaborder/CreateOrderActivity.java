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
    private TextView vTextViewDelivery;
    private CheckBox vCheckBoxSauce;
    private CheckBox vCheckBoxHot;
    private CheckBox vCheckBoxSeparately;
    private Spinner vSpinnerMain;
    private Spinner vSpinnerFree;
    private RadioButton vRadioButtonOurPlace;
    private RadioButton vRadioButtonHome;
    private RadioButton vRadioButtonFree;

    private String name;
    private String password;
    private String food;
    private StringBuilder vBuilderAdditions;

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

        vTextViewEditions = findViewById(R.id.textViewAdditions);
        String editions = String.format(getString(R.string.extras), food);
        vTextViewEditions.setText(editions);
        vTextViewDelivery = findViewById(R.id.textViewDeliverer);

        vCheckBoxSauce = findViewById(R.id.checkboxSauce);
        vCheckBoxHot = findViewById(R.id.checkboxHot);
        vCheckBoxSeparately = findViewById(R.id.checkboxSeparately);
        vSpinnerMain = findViewById(R.id.spinnerMainDelivery);
        vSpinnerFree = findViewById(R.id.spinnerFreeDelivery);
        vRadioButtonOurPlace = findViewById(R.id.radioButtonOurPlace);
        vRadioButtonHome = findViewById(R.id.radioButtonAtHome);
        vRadioButtonFree = findViewById(R.id.radioButtonFree);

        vBuilderAdditions = new StringBuilder();

    }

    public void onClickChangeFood(View view) {
        RadioButton button = (RadioButton)view;
        int id = button.getId();
        if (id == R.id.radioButtonShaurma) {
            food = getString(R.string.shaurma);
            vCheckBoxSeparately.setVisibility(View.VISIBLE);

            vCheckBoxHot.setChecked(false);

        } else if (id == R.id.radioButtonDener) {
            food = getString(R.string.dener);
            vCheckBoxSeparately.setVisibility(View.INVISIBLE);

            vCheckBoxHot.setChecked(false);

        } else if (id == R.id.radioButtonFree) {
            food = getString(R.string.free);
            vCheckBoxSeparately.setVisibility(View.INVISIBLE);

            vCheckBoxHot.setChecked(true);
        }
        String additions = String.format(getString(R.string.extras), food);
        vTextViewEditions.setText(additions);
    }

    public void onClickChangeDelivery(View view) {
        RadioButton button2 = (RadioButton)view;
        int id = button2.getId();
        if (id == R.id.radioButtonOurPlace) {
            vTextViewDelivery.setVisibility(View.INVISIBLE);
            vSpinnerFree.setVisibility(View.INVISIBLE);
            vSpinnerMain.setVisibility(View.INVISIBLE);
        } else if (id == R.id.radioButtonAtHome && id == R.id.radioButtonFree) {
            vTextViewDelivery.setVisibility(View.VISIBLE);
            vSpinnerFree.setVisibility(View.VISIBLE);
            vSpinnerMain.setVisibility(View.INVISIBLE);
        }
        else if (id == R.id.radioButtonAtHome && id != R.id.radioButtonFree) {
            vTextViewDelivery.setVisibility(View.VISIBLE);
            vSpinnerFree.setVisibility(View.INVISIBLE);
            vSpinnerMain.setVisibility(View.VISIBLE);
        }
    }

    public void onClickSendOrder(View view) {
        vBuilderAdditions.setLength(0);
        if (vCheckBoxSauce.isChecked()) {
            vBuilderAdditions.append(R.string.sauсe).append(" ");
        }
        if (vCheckBoxHot.isChecked()) {
            vBuilderAdditions.append(R.string.hot).append(" ");
        }
        if (vCheckBoxSeparately.isChecked() && !food.equals(getString(R.string.free))) {
            vBuilderAdditions.append(R.string.separately).append(" ");
        }
        String optionOfDelivery = "";
        if (vRadioButtonHome.isChecked() && !vRadioButtonFree.isChecked()) {
            optionOfDelivery = vSpinnerMain.getSelectedItem().toString();
        } else {
            optionOfDelivery = vSpinnerFree.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order),name,password,food,optionOfDelivery);
        String additions ;
        if (vBuilderAdditions.length() > 0) {
            additions = getString(R.string.additions_added) + vBuilderAdditions.toString();
        } else {
            additions = "";
        }
        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }


}
