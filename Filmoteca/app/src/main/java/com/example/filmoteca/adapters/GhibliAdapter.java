package com.example.filmoteca.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.filmoteca.R;
import com.example.filmoteca.models.FilmGhibli;

import java.util.ArrayList;

public class GhibliAdapter extends ArrayAdapter<FilmGhibli> {

    int layoutResourceId;
    Context context;
    ArrayList<FilmGhibli> data;
    TextView textView;

    public GhibliAdapter(Context context, int layoutResourceId, ArrayList<FilmGhibli> data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        textView=row.findViewById(R.id.rowGhibli);
        FilmGhibli f =data.get(position);
        textView.setText(f.getTitle());
        return row;
    }
}
