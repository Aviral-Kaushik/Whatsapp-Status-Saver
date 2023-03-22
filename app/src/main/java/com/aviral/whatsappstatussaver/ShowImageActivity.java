package com.aviral.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ShowImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        String imagePath = getIntent().getStringExtra(getString(R.string.image_extra));

        ImageView showImage = findViewById(R.id.show_image);

        Glide.with(this)
                .load(imagePath)
                .into(showImage);

    }
}