package com.example.practicat4.Service;

import com.example.practicat4.Entities.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MovieService {
    @GET("movies")
    Call<List<Movie>> GetMovies();
    @POST("movies")
    Call<Movie> createMovie(@Body Movie movie);
    @PUT("movies/{id}")
    Call<Movie> Update(@Path("id") int id, @Body Movie movie);
    @DELETE("movies/{id}")
    Call<Movie> Delete(@Path("id") int id, @Body Movie movie);
}
