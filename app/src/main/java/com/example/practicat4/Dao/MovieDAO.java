package com.example.practicat4.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practicat4.Entities.Movie;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM movies")
    List<Movie> getAll();
    @Query("SELECT *FROM movies WHERE id = :idContact")
    Movie find (int idContact);
    //Creary agregar dato a bd
    @Insert
    void create(Movie movie);
    @Update
    void updateBD(Movie movie);
    @Delete
    void delete(Movie movie);
}
