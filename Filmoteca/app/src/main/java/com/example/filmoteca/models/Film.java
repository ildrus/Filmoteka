package com.example.filmoteca.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "film")
public class Film {

    @PrimaryKey
    @NonNull
    String id;
    @ColumnInfo(name="title")
    String title;
    @ColumnInfo(name="description")
    String description;
    @ColumnInfo(name="year")
    int year;
    @ColumnInfo(name="note")
    int note;
    @ColumnInfo(name="urlImage")
    String urlImage;

    public Film(String title,String description,int year,int note,String urlImage){
        this.id= UUID.randomUUID().toString();
        this.title=title;
        this.description=description;
        this.year=year;
        this.note=note;
        this.urlImage=urlImage;
    }

    @NonNull
    public String getId(){
        return this.id;
    }

    public void setId(@NonNull String id){
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public int getYear(){
        return this.year;
    }

    public int getNote(){
        return this.note;
    }

    public String getUrlImage(){
        return this.urlImage;
    }
}
