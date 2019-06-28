package com.example.filmoteca;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.filmoteca.adapters.FilmAdapter;
import com.example.filmoteca.controller.FilmController;
import com.example.filmoteca.inspirateclass.Inspirate;
import com.example.filmoteca.models.Film;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView filmsView;
    FilmController controller;
    ArrayList<Film> filmListArray;
    ArrayAdapter adapter;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        loguin();
        inicializate();
        getFilmList();
        oneClick();
    }

    private void loguin(){
        String email=prefs.getString("email","");
        String password=prefs.getString("password","");
        if(email.equals("") && password.equals("")){
            Intent intent= new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }
    }

    private void inicializate(){
        filmsView=findViewById(R.id.listFilm);
        filmListArray=new ArrayList<Film>();
        controller=FilmController.get(this);
        adapter= new FilmAdapter(this,R.layout.row,filmListArray);
        filmsView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.createFilm:
                createFilm();
                return (true);
            case R.id.inspiration:
                layoutInspirate();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private void createFilm(){
        Intent intent= new Intent(MainActivity.this,CreateFilmForm.class);
        startActivity(intent);
    }
    private void layoutInspirate(){
        Intent intent= new Intent(MainActivity.this, Inspirate.class);
        startActivity(intent);
    }

    private void getFilmList(){
        filmListArray.clear();
        filmListArray.addAll(controller.getFilmsList());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFilmList();
    }

    private void oneClick(){
        filmsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        int itemPosition = i;
                        Film film = (Film) filmsView.getItemAtPosition(i);
                        String idFilm =film.getId();
                        detailLayout(idFilm);
                    }
                }
        );
    }

    private void detailLayout(String idFilm){
        Intent intent= new Intent(MainActivity.this,FilmDetail.class);
        intent.putExtra("idFilm",idFilm);
        startActivity(intent);
    }
}
