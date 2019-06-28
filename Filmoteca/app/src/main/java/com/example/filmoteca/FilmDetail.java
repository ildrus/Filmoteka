package com.example.filmoteca;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmoteca.controller.FilmController;
import com.example.filmoteca.models.Film;
import com.squareup.picasso.Picasso;

public class FilmDetail extends AppCompatActivity {

    FilmController controller;
    TextView textTitleResult,textDescriptionResult,textNoteResult,textYearResult;
    String idFilm,title,description,note,year,image;
    ImageView imageView;
    Activity activity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        inicializate();
        showFilm();
    }

    private void inicializate(){
        controller=FilmController.get(this);
        textTitleResult=findViewById(R.id.textTitleResult);
        textDescriptionResult=findViewById(R.id.textDescriptionResult);
        textNoteResult=findViewById(R.id.textNoteResult);
        textYearResult=findViewById(R.id.textYearResult);
        imageView=findViewById(R.id.image);
        idFilm=getIntent().getStringExtra("idFilm");
    }

    private void showFilm(){
        Film film=controller.getFilm(idFilm);
        createStrings(film);
        setTexts();
    }

    private void createStrings(Film film){
        title=film.getTitle();
        description=film.getDescription();
        note=""+film.getNote();
        year=""+film.getYear();
        image=film.getUrlImage();
    }

    private void setTexts(){
        textTitleResult.setText(title);
        textDescriptionResult.setText(description);
        textNoteResult.setText(note);
        textYearResult.setText(year);
        Picasso.get().load(image).resize(400, 400).into(imageView);
    }

    public void deleteFilm(View view){
        Film film=controller.getFilm(idFilm);
        controller.deleteFilm(film);
        finish();
        Toast.makeText(activity, getString(R.string.delete_successful), Toast.LENGTH_LONG).show();
    }
}
