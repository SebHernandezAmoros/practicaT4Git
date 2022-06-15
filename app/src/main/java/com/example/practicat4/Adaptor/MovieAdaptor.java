package com.example.practicat4.Adaptor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicat4.Entities.Movie;
import com.example.practicat4.R;

import java.util.List;

public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.StringViewHolder>{
    List<Movie>movies;
    public MovieAdaptor(List<Movie> movies){
    this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdaptor.StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        StringViewHolder vh = new StringViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdaptor.StringViewHolder holder, int position) {
        View view = holder.itemView;
        Movie movie =movies.get(position);
        TextView tvTitulo = view.findViewById(R.id.tvTitulo);
        TextView tvSinopsis = view.findViewById(R.id.tvSinopsis);
        ImageView imgView = view.findViewById(R.id.imgView);
        View Ly = view.findViewById(R.id.llEsquema);

        tvTitulo.setText(movie.titulo);
        tvSinopsis.setText(movie.sinopsis);
        //Imagen asignar
        byte[] decodedString = Base64.decode(movie.imagen, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //Mostrar la imagen base64
        imgView.setImageBitmap(decodedByte);

        Ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class StringViewHolder extends RecyclerView.ViewHolder {
        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
