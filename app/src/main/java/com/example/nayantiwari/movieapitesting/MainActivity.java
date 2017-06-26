package com.example.nayantiwari.movieapitesting;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SomeAdapter.ListItemClickListener, LoaderManager.LoaderCallbacks<List<MovieItem>> {

    public static final String MOVIE_TITLE_KEY = "MOVIE_TITLE_KEY";
    public static final String MOVIE_POSTER_KEY = "MOVIE_POSTER_KEY";
    public static final String MOVIE_DESC_KEY = "MOVIE_DESC_KEY";
    public static final String MOVIE_VOTE_COUNT_KEY = "MOVIE_VOTE_COUNT_KEY";
    public static final String MOVIE_RELEASE_DATE_KEY = "MOVIE_RELEASE_DATE_KEY";
    private List<MovieItem> data;

    private String sortOrder = "default";

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);

        SomeAdapter someAdapter = new SomeAdapter(this, new ArrayList<MovieItem>(), this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(someAdapter);

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @Override
    public Loader<List<MovieItem>> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader: Sorting By: " + sortOrder);
        String movieUrl = NetworkUtils.buildUrl(sortOrder).toString();
        Log.i(TAG, "onCreateLoader: " + movieUrl);
        return new MovieLoader(this, movieUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<MovieItem>> loader, List<MovieItem> data) {
        SomeAdapter someAdapter = (SomeAdapter) recyclerView.getAdapter();
        Log.i(TAG, "onLoadFinished: Loaded " + data.size() + " items.");
        this.data = data;
        someAdapter.setMovieItems(data);
        someAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<MovieItem>> loader) {
        SomeAdapter someAdapter = (SomeAdapter) recyclerView.getAdapter();
        someAdapter.setMovieItems(new ArrayList<MovieItem>());
        someAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        String wtf = "wtf is this";
        Intent intent = new Intent(MainActivity.this, MovieInfoActivity.class);
        MovieItem movieItem = data.get(clickedItemIndex);
        intent.putExtra(MOVIE_TITLE_KEY, movieItem.getTitle());
        intent.putExtra(MOVIE_POSTER_KEY, movieItem.getMoviePoster());
        intent.putExtra(MOVIE_DESC_KEY, movieItem.getOverview());
        intent.putExtra(MOVIE_VOTE_COUNT_KEY, movieItem.getVote_average());
        intent.putExtra(MOVIE_RELEASE_DATE_KEY, movieItem.getReleaseDate());

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemChosen = item.getItemId();
        if (menuItemChosen == R.id.rating) {
            sortOrder = "rating";
            NetworkUtils.buildUrl("rating");
            getSupportLoaderManager().restartLoader(1, null, this).forceLoad();
            return true;
        } else {
            sortOrder = "popularity";
            NetworkUtils.buildUrl("popularity");
            getSupportLoaderManager().restartLoader(1, null, this).forceLoad();
            return true;
        }
    }
}
