package com.example.myapplication.composition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FavoriteCompositionAdapter;
import com.example.myapplication.model.CompositionInfo;
import com.example.myapplication.network.NetworkService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteComposition extends AppCompatActivity  {

    List<CompositionInfo> items = new ArrayList<>();
    FavoriteCompositionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_composition);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Избранное");
        }


        RecyclerView recycler = (RecyclerView)findViewById(R.id.favoriteRecycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoriteCompositionAdapter(recycler, this, items);
        recycler.setAdapter(adapter);

//        NetworkService.getInstance()
//                .getJSONApi()
//                .getCompositions()
//                .enqueue(new Callback<List<CompositionInfo>>() {
//                    @Override
//                    public void onResponse(Call<List<CompositionInfo>> call, Response<List<CompositionInfo>> response) {
//
//                        List<CompositionInfo> compositions = response.body() != null ? response.body() : Collections.emptyList();
//                        items.addAll(compositions);
//                        System.out.println("items: " + items.toString());
//                        System.out.println("Дерг");
//                        recycler.setAdapter(adapter);
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<CompositionInfo>> call, Throwable t) {
//                        System.out.println("FAILURE");
//                    }
//                });

        adapter.setLoadMore(() -> {
            if (items.size() <= 100) {
               // items.add(null);
                //adapter.notifyItemInserted(items.size()-1);
                NetworkService.getInstance()
                        .getJSONApi()
                        .getCompositions()
                        .enqueue(new Callback<List<CompositionInfo>>() {
                            @Override
                            public void onResponse(Call<List<CompositionInfo>> call, Response<List<CompositionInfo>> response) {


//                                items.remove(items.size() -1);
//                                adapter.notifyItemRemoved(items.size());




                                List<CompositionInfo> compositions = response.body() != null ? response.body() : Collections.emptyList();

                                for(CompositionInfo compositionInfo : compositions) {
                                    items.add(compositionInfo);
                                    adapter.notifyItemInserted(items.size() -1);
                                }

                                System.out.println("items: " + items.toString());
                                System.out.println("Дерг");

                                adapter.notifyDataSetChanged();
                                adapter.setLoaded();
                            }

                            @Override
                            public void onFailure(Call<List<CompositionInfo>> call, Throwable t) {
                                System.out.println("FAILURE");
                            }
                        });
            } else {
                Toast.makeText(FavoriteComposition.this, "Load data completed !", Toast.LENGTH_SHORT).show();
            }
        });

//        if (items.isEmpty()) {
//            System.out.println("items is empty");
//        }
//        recycler.setAdapter(adapter);
        random5Data();
        items.add(new CompositionInfo(1, "Composition", "author"));



        // Set Load more event



    }

    private void random5Data() {
        // Random data
        for (int i = 0; i< 5; i++) {
            String name  = UUID.randomUUID().toString();
            CompositionInfo compositionInfo = new CompositionInfo(0, name, name.length() + "");
            items.add(compositionInfo);
        }
    }

}
