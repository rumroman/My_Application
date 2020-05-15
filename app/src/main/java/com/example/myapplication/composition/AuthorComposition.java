package com.example.myapplication.composition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;

public class AuthorComposition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_composition);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Исполнители");
        }
    }
}
