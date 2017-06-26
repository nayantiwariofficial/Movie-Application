package com.example.nayantiwari.movieapitesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_DESC_KEY;
import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_POSTER_KEY;
import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_TITLE_KEY;

public class MovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        TextView title_info = (TextView) findViewById(R.id.title_info);
        ImageView iv_movie_info = (ImageView) findViewById(R.id.iv_movie_info);

        String movie_poster = null;

        if (getIntent().hasExtra(MOVIE_POSTER_KEY)) {
            movie_poster = getIntent().getStringExtra(MOVIE_POSTER_KEY);
        }

        Glide.with(this).load("http://image.tmdb.org/t/p/original/" + movie_poster + "?api_key=622c017c1fac858c7683036985247ab5")
                .centerCrop()
                .placeholder(R.drawable.placeholderimage)
                .into(iv_movie_info);


        if (getIntent().hasExtra(MOVIE_TITLE_KEY)) {
            String movie_title = getIntent().getStringExtra(MOVIE_TITLE_KEY);
            title_info.setText(movie_title);
        }

        if (getIntent().hasExtra(MOVIE_DESC_KEY)) {
            String movie_overview = getIntent().getStringExtra(MOVIE_DESC_KEY);

        }

    }
}
