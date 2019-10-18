package com.example.kebaborder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText vEditTextName;
    private EditText vEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vEditTextName = findViewById(R.id.editText_name);
        vEditTextPassword = findViewById(R.id.editText_password);

    }

    public void onClickCreateOrder(View view) {
        String name = vEditTextName.getText().toString().trim();
        String password = vEditTextPassword.getText().toString().trim();

        if (!name.isEmpty() && !password.isEmpty() ) {
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            startActivity(intent);
        } else if (name.isEmpty()) {
            Toast.makeText(this,R.string.toast_name,Toast.LENGTH_LONG).show();
        }
        else if (password.isEmpty()) {
            Toast.makeText(this,R.string.toast_password,Toast.LENGTH_LONG).show();
        }
    }
}
