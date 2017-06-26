package com.example.nayantiwari.movieapitesting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by nayantiwari on 6/26/17.
 */

public class MovieLoader extends AsyncTaskLoader<List<MovieItem>> {

    private String mUrl;
    private boolean favoriteValue;

    public MovieLoader(Context context, String url, boolean favoriteValue) {
        super(context);
        mUrl = url;
        this.favoriteValue = favoriteValue;
    }

    @Override
    public List<MovieItem> loadInBackground() {
        if (mUrl == null) {
        return null;
        }

        return QueryUtils.fetchMovieData(mUrl, favoriteValue);
    }


}
