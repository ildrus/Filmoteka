package com.example.filmoteca;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filmoteca.controller.FilmController;
import com.example.filmoteca.models.Film;

public class CreateFilmForm extends AppCompatActivity {

    EditText editTitle,editDescription,editYear,editNote,editUrlImage;
    String title,description,sYear,sNote,urlImage;
    int year=0,note=0;
    Activity activity=this;
    FilmController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_film_form);
        inicializate();
    }

    private void inicializate(){
        editTitle=findViewById(R.id.editTitle);
        editDescription=findViewById(R.id.editDescription);
        editYear=findViewById(R.id.editYear);
        editNote=findViewById(R.id.editNote);
        editUrlImage=findViewById(R.id.editUrlImage);
        controller= FilmController.get(this);
    }

    private boolean checkFilm(){
        boolean itsOk=true;
        if(title.equals("")){editTitle.setError(getString(R.string.e_title));itsOk=false;}
        if(description.equals("")){editDescription.setError(getString(R.string.e_description));itsOk=false;}
        if(sYear.equals("")) { editYear.setError(getString(R.string.e_year));itsOk=false; }
        else{year=Integer.parseInt(sYear);}
        if(sNote.equals("")){ editNote.setError(getString(R.string.e_note_empty));itsOk=false;}
        else{note=Integer.parseInt(sNote);if(note<0||note>5){editNote.setError(getString(R.string.e_note_range));itsOk=false;}}
        if(urlImage.equals("")){editUrlImage.setError(getString(R.string.e_url_image));itsOk=false;}
        return itsOk;
    }

    public void createFilm(View view){
        inicializateVariables();
        if(checkFilm()){
            Film film =new Film(title,description,year,note,urlImage);
            controller.createFilm(film);
            Toast.makeText(activity, getString(R.string.create_successful), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void inicializateVariables(){
        title=editTitle.getText().toString();
        description=editDescription.getText().toString();
        sYear=editYear.getText().toString();
        sNote=editNote.getText().toString();
        urlImage=editUrlImage.getText().toString();
    }
}
