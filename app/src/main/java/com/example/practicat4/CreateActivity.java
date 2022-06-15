package com.example.practicat4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.practicat4.Entities.Movie;
import com.example.practicat4.Factories.RetrofitFactory;
import com.example.practicat4.Service.MovieService;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateActivity extends AppCompatActivity {
    static final int REQUEST_PICK_IMAGE = 1001;
    String encodedImage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Button btnBuscarGallery = findViewById(R.id.btnGaleria);
        EditText ettTitulo = findViewById(R.id.ettTitulo);
        EditText ettSinopsis = findViewById(R.id.ettSinopsis);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnBuscarGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new Movie();
                movie.titulo = String.valueOf(ettTitulo.getText());
                movie.sinopsis = String.valueOf(ettSinopsis.getText());
                movie.imagen = encodedImage;

                Retrofit retrofit = RetrofitFactory.build();
                MovieService service = retrofit.create(MovieService.class);
                Call<Movie> call = service.createMovie(movie);
                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Log.i("APP_VJ20221",new Gson().toJson(response.body()));
                        Log.i("APP_VJ20221","->"+new Gson().toJson(movie));
                        Log.i("APP_VJ20221","Se registro");

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.i("APP_VJ20221","No se pudo conectar");
                    }
                });
            }
        });
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {

            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            Bitmap imageBitmap = BitmapFactory.decodeStream(bufferedInputStream);

            //Conversion de bitmap(foto) a base 64
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            //guardar el base64 en un string
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Log.i("APP_VJ20221",encodedImage);

        }
    }
}