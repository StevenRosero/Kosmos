package com.fct.kosmos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fct.kosmos.R;

public class LoginActivity extends AppCompatActivity {

    private Button Boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Boton = (Button) findViewById(R.id.button2);

        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistreActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}