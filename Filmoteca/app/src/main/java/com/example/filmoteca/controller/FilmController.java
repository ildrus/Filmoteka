package com.example.filmoteca.controller;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.filmoteca.database.FilmDao;
import com.example.filmoteca.database.FilmDatabase;
import com.example.filmoteca.models.Film;

import java.util.List;

public class FilmController {

    private static FilmController controller;
    private FilmDao filmDao;

    private FilmController(Context context){
        Context appContext=context.getApplicationContext();
        FilmDatabase database= Room.databaseBuilder(appContext, FilmDatabase.class,"film").allowMainThreadQueries().build();
        filmDao=database.getFilmDao();
    }

    public static FilmController get(Context context){
        if(controller==null){
            controller=new FilmController(context);
        }
        return controller;
    }

    public List<Film> getFilmsList(){return filmDao.getFilmsList();}

    public Film getFilm(String id){return filmDao.getFilm(id);}

    public void createFilm(Film film){ filmDao.createFilm(film);}

    public void deleteFilm(Film film){ filmDao.deleteFilm(film);}
}
