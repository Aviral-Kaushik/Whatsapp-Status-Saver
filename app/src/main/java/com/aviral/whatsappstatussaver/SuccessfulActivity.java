package com.aviral.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);

        ImageView backToMain = findViewById(R.id.back);

        backToMain.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }
}