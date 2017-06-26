package com.example.nayantiwari.movieapitesting;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nayantiwari on 6/26/17.
 */

public class ExtractJSON {

    private static final String TAG = "ExtractJSON";

    public static List<MovieItem> extractFeatureFromJson(String movieJSON, boolean favoriteValue) {


        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }

        List<MovieItem> movie = new ArrayList<>();

        try {

            Log.i(TAG, "extractFeatureFromJson: " + movieJSON);

            JSONObject baseJsonResponse = new JSONObject(movieJSON);
            JSONArray movieArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < movieArray.length(); i++) {

                // Get a single movie at position i within the list of movies
                JSONObject currentMovie = movieArray.getJSONObject(i);  // Extract the value for the key called "title"

                String title = currentMovie.getString("title");
                Log.i(TAG, "extractFeatureFromJson: " + title);
                String moviePoster = currentMovie.getString("poster_path");
                String overview = currentMovie.getString("overview");
                String releaseDate = currentMovie.getString("release_date");
                double vote_avg = currentMovie.getDouble("vote_average");
//                long movieId = currentMovie.getLong("id");

                MovieItem movieItem = new MovieItem(title, moviePoster, overview, releaseDate, vote_avg, favoriteValue);
                Log.i(TAG, "extractFeatureFromJson: " + title +" " + moviePoster + " " + overview + " " + releaseDate + " " + vote_avg + " " + favoriteValue);


                movie.add(movieItem);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the movie JSON results", e);
        }

        return movie;
    }
}
