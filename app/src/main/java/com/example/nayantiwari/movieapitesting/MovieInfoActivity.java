package com.example.nayantiwari.movieapitesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_DESC_KEY;
import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_FAVOURITE_KEY;
import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_POSTER_KEY;
import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_RELEASE_DATE_KEY;
import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_TITLE_KEY;
import static com.example.nayantiwari.movieapitesting.MainActivity.MOVIE_VOTE_COUNT_KEY;

public class MovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        TextView title_info = (TextView) findViewById(R.id.title_info);
        ImageView iv_movie_info = (ImageView) findViewById(R.id.iv_movie_info);
        TextView overview_info = (TextView) findViewById(R.id.overview_info);
        TextView release_date_info = (TextView) findViewById(R.id.release_date_info);
        TextView vote_average_info = (TextView) findViewById(R.id.vote_average_info);
        Button favorite_info_button = (Button) findViewById(R.id.favorite_button);

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
            String movie_overview = "Overview: " + getIntent().getStringExtra(MOVIE_DESC_KEY);
            overview_info.setText(movie_overview);
        }

        if (getIntent().hasExtra(MOVIE_VOTE_COUNT_KEY)) {
            String movie_vote_count = "Rating: " + getIntent().getDoubleExtra(MOVIE_VOTE_COUNT_KEY, 0.0) + "/10";
            vote_average_info.setText(movie_vote_count);
        }

        if (getIntent().hasExtra(MOVIE_RELEASE_DATE_KEY)) {
            String movie_release_date = "Release date: " + getIntent().getStringExtra(MOVIE_RELEASE_DATE_KEY);
            release_date_info.setText(movie_release_date);
        }

        if (getIntent().hasExtra(MOVIE_FAVOURITE_KEY)) {
            boolean favorite_movie = getIntent().getBooleanExtra(MOVIE_FAVOURITE_KEY, false);
            if (!favorite_movie) {
                favorite_info_button.setText(R.string.add_to_favorites);
            } else {
                favorite_info_button.setText(R.string.remove_from_favorites);
            }
        }

    }
}
