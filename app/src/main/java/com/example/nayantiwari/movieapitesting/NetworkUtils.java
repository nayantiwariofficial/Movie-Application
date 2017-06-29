package com.example.nayantiwari.movieapitesting;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nayantiwari on 6/25/17.
 */

public class NetworkUtils {

    private final static String MOVIE_BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "677f4650f749724420f60f9aa0c83b2b";

    private final static String AFTER_MOVIE_BASE_URL_WITHOUT_SORT = "discover";
    private final static String MOVIE = "movie";
    private final static String POPULAR = "popular";
    private final static String TOP_RATED = "top_rated";


//    final static String MOVIE_ID_BASE_URL = "https://api.themoviedb.org/3/movie/";

    final static String PARAM_QUERY_MOVIE = "api_key";
    final static String SORT_BY = "sort_by";
    private static final String LOG_TAG = "Network Utils";


    public static URL buildUrl(String sortBy) {
        Uri builtUri;
        switch (sortBy) {
            case "popularity":
                builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendPath(MOVIE)
                        .appendPath(POPULAR)
                        .appendQueryParameter(PARAM_QUERY_MOVIE, API_KEY)
                        .build();

                break;
            case "rating":
                builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendPath(MOVIE)
                        .appendPath(TOP_RATED)
                        .appendQueryParameter(PARAM_QUERY_MOVIE, API_KEY)
                        .build();
                break;
            default:
                builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendPath(AFTER_MOVIE_BASE_URL_WITHOUT_SORT)
                        .appendPath(MOVIE)
                        .appendQueryParameter(PARAM_QUERY_MOVIE, API_KEY)
                        .build();
                break;
        }

        Log.i(LOG_TAG, "buildUrl: " + builtUri);

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
