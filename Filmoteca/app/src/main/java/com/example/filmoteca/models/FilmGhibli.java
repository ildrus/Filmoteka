package com.example.filmoteca.models;

public class FilmGhibli {

    String id,title;

    public FilmGhibli(){}

    public FilmGhibli(String id,String title){
        this.id=id;
        this.title=title;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }
}
