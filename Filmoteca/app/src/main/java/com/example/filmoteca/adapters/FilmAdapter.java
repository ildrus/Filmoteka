package com.example.filmoteca.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.filmoteca.R;
import com.example.filmoteca.models.Film;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film> {
    int layoutResourceId;
    Context context;
    ArrayList<Film> data;
    TextView textNote,textTitle;

    public FilmAdapter(Context context,int layoutResourceId,ArrayList<Film> data){
        super(context,layoutResourceId,data);
        this.layoutResourceId=layoutResourceId;
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        textNote=row.findViewById(R.id.textNote);
        textTitle=row.findViewById(R.id.textTitle);
        Film film =data.get(position);

        setTextInTextView(film);

        colorsFilms(film);

        return row;
    }

    private void setTextInTextView(Film film){
        textNote.setText(""+film.getNote());
        textTitle.setText(film.getTitle());
    }

    private void colorsFilms(Film film){
        if(film.getNote()<2){
            textTitle.setTextColor(context.getResources().getColor(R.color.red));
        }
        else if(film.getNote()<4){
            textTitle.setTextColor(context.getResources().getColor(R.color.black));
        }
        else{
            textTitle.setTextColor(context.getResources().getColor(R.color.green));
        }
    }

}
