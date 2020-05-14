package com.example.myapplication.composition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.myapplication.Composition;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ILoadMore;
import com.example.myapplication.adapter.MyAdapter;
import com.example.myapplication.model.CompositionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FavoriteComposition extends AppCompatActivity {

    List<CompositionInfo> items = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_composition);

        // Random data
        random10Data();

        RecyclerView recycler = (RecyclerView)findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(recycler, this, items);
        recycler.setAdapter(adapter);

        // Set Load more event
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 20) {
                    items.add(null);
                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size() -1);
                            adapter.notifyItemRemoved(items.size());

                            // Random more data

                            int index = items.size();
                            int end = index+10;
                            for (int i=index; i<end; i++){
                                String name = UUID.randomUUID().toString();
                                CompositionInfo compositionInfo = new CompositionInfo(name, name.length() +"");
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();

                        }
                    }, 5000); // Time to load
                } else {
                    Toast.makeText(FavoriteComposition.this, "Load data completed !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recycler.addOnItemTouchListener();


    }

    private void random10Data() {
        // Random data
        for (int i = 0; i< 10; i++) {
            String name  = UUID.randomUUID().toString();
            CompositionInfo compositionInfo = new CompositionInfo(name, name.length() + "");
            items.add(compositionInfo);
        }
    }
}
