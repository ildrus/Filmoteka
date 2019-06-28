package com.example.filmoteca.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.filmoteca.models.Film;

import java.util.List;

@Dao
public interface FilmDao {

    @Query("Select * From film")
    List<Film> getFilmsList();

    @Query("Select * From film Where id like :uuid")
    Film getFilm(String uuid);

    @Insert
    void createFilm(Film film);

    @Delete
    void deleteFilm(Film film);

}
