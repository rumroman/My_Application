package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.composition.AuthorComposition;
import com.example.myapplication.composition.FavoriteComposition;
import com.example.myapplication.composition.JenreComposition;
import com.example.myapplication.composition.PopComposition;
import com.example.myapplication.model.CompositionInfo;
import com.example.myapplication.network.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Composition extends AppCompatActivity implements View.OnClickListener {

    CardView favoriteCard, popCard, authorCard, jenreCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composition);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        favoriteCard = findViewById(R.id.favoriteCard);
        popCard = findViewById(R.id.popCard);
        authorCard = findViewById(R.id.authorCard);
        jenreCard = findViewById(R.id.jenreCard);

        favoriteCard.setOnClickListener(this);
        popCard.setOnClickListener(this);
        authorCard.setOnClickListener(this);
        jenreCard.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.favoriteCard:
                i = new Intent(this, FavoriteComposition.class);
                startActivity(i);
                break;
            case R.id.popCard:
                i = new Intent(this, PopComposition.class);
                startActivity(i);
                break;
            case R.id.authorCard:
                i = new Intent(this, AuthorComposition.class);
                startActivity(i);
                break;
            case R.id.jenreCard:
                i = new Intent(this, JenreComposition.class);
                startActivity(i);
                break;
        }

    }
}
