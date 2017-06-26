package com.example.nayantiwari.movieapitesting;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nayantiwari on 6/25/17.
 */

public class NetworkUtils {

    final static String MOVIE_BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_KEY = "677f4650f749724420f60f9aa0c83b2b";

//    final static String MOVIE_ID_BASE_URL = "https://api.themoviedb.org/3/movie/";

    final static String PARAM_QUERY_MOVIE = "api_key";
    final static String SORT_BY = "sort_by";


    public static URL buildUrl(String sortBy) {
        Uri builtUri;
        switch (sortBy) {
            case "popularity":
                sortBy = "popularity.desc";
                builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(PARAM_QUERY_MOVIE, API_KEY)
                        .appendQueryParameter(SORT_BY, sortBy)
                        .build();
                break;
            case "rating":
                sortBy = "vote_average.desc";
                builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(PARAM_QUERY_MOVIE, API_KEY)
                        .appendQueryParameter(SORT_BY, sortBy)
                        .build();
                break;
            default:
                builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(PARAM_QUERY_MOVIE, API_KEY)
                        .build();
                break;
        }


        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
