package com.example.myapplication.composition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;

public class JenreComposition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenre_composition);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Жанры");
        }
    }
}
