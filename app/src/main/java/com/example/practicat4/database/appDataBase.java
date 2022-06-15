package com.example.practicat4.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.practicat4.Dao.MovieDAO;
import com.example.practicat4.Entities.Movie;

@Database(entities = {Movie.class }, version = 1)
public abstract class appDataBase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
    //En caso de agregar mas tablas aqui su DAO y arriba la vlase declarada(linea 9)

    public static appDataBase getDataBase(Context context){
        return Room.databaseBuilder(context, appDataBase.class, "com.example.practicat4.database.movies_db")
                //Permiso para usar el hilo principal
                .allowMainThreadQueries()
                .build();
    }
}