package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    SearchView mySearchView;
    ListView listView;

    CompositionAdapter adapter;

    String compositionNames[] = {"One", "Two", "Three", "For", "Five"};
    String authorNames[] = {"One", "Two", "Three", "For", "Five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mySearchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.myList);

        LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        adapter = new CompositionAdapter(this, compositionNames, authorNames, layoutInflater);


        listView.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);


                return false;
            }
        });

    }

}
