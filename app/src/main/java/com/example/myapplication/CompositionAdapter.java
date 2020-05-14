package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CompositionAdapter extends ArrayAdapter<String> {

    Context context;
    String[] compositionName;
    String[] authorName;
    LayoutInflater layoutInflater;

    CompositionAdapter(Context context, String[] title, String[] description, LayoutInflater layoutInflater) {
        super(context, R.layout.row, R.id.textView1, title);
        this.context = context;
        this.compositionName = title;
        this.authorName = description;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        TextView myTitle = row.findViewById(R.id.textView1);
        TextView myDescription = row.findViewById(R.id.textView2);

        myTitle.setText(compositionName[position]);
        myDescription.setText(authorName[position]);

        return row;
    }
}
