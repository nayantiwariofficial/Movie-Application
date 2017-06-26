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

    public MovieItem(String title, String moviePoster, String overview, String releaseDate, double vote_average) {
        this.title = title;
        this.moviePoster = moviePoster;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.vote_average = vote_average;
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
}
