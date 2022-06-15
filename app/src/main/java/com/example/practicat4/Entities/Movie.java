package com.example.practicat4.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public String sinopsis;
    public String imagen;

    public Movie() {
    }

    public Movie(String titulo, String sinopsis, String imagen) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
    }

    public Movie(int id, String titulo, String sinopsis, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
    }
}
