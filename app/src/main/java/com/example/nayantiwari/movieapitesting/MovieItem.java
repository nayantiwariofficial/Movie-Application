package com.example.nayantiwari.movieapitesting;

import java.io.Serializable;

/**
 * Created by nayantiwari on 6/25/17.
 */

public class MovieItem implements Serializable {
    private String title;
    private String moviePoster;
    private String overview;
    private String releaseDate;
    private double vote_average;
    private boolean favourite;

    public MovieItem(String title, String moviePoster, String overview, String releaseDate, double vote_average, boolean favourite) {
        this.title = title;
        this.moviePoster = moviePoster;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.vote_average = vote_average;
        this.favourite = favourite;
    }

    public String getTitle() {
        return title;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVote_average() {
        return vote_average;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
