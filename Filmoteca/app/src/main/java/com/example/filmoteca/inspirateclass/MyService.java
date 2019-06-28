package com.example.filmoteca.inspirateclass;

import com.example.filmoteca.models.FilmGhibli;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MyService {

    @GET("films")
    Call<List<FilmGhibli>> getFilms();

}
