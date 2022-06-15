package com.example.practicat4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.practicat4.Adaptor.MovieAdaptor;
import com.example.practicat4.Dao.MovieDAO;
import com.example.practicat4.Entities.Movie;
import com.example.practicat4.Factories.RetrofitFactory;
import com.example.practicat4.Service.MovieService;
import com.example.practicat4.database.appDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    List<Movie> movies = new ArrayList<>();
    appDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = RetrofitFactory.build();

        MovieService service = retrofit.create(MovieService.class);
        Call<List<Movie>> call= service.GetMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                Log.i("APP_VJ20221","Respuesta correcta");
                Log.i("APP_VJ20221",new Gson().toJson(response.body()));

                //Obtencion de lista y envio al adapter
                movies = response.body();
                registrarMovies(movies);

                MovieAdaptor adapter = new MovieAdaptor(movies);
                //Obtencion del recyclerview y envio del adapter
                RecyclerView rv = findViewById(R.id.rvLista);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv.setHasFixedSize(true);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.i("APP_VJ20221","No pudo conectar con el servicio");
            }
        });
        FloatingActionButton fabButton = findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(intent);
            }
        });
    }
    void registrarMovies(List<Movie> movieList){
        db= appDataBase.getDataBase(getApplicationContext());
        MovieDAO movieDAO = db.movieDAO();
        for (Movie Mov:movieList){
            if(movieDAO.find(Mov.id )== null){
                if(movieDAO.find((Mov.id)+1) == null){
                    movieDAO.create(Mov);
                }
                else{
                    movieDAO.delete(Mov);
                }
            }
            else{
                movieDAO.updateBD(Mov);
            }

        }

    }
}