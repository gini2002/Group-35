package use_case.GetDetailMovie;

import entity.Movie;

public class GetDetailMovieOutputData {
    private Movie movie;

    public GetDetailMovieOutputData(Movie movie){
        this.movie = movie;
    }

    public Movie getMovie(){
        return movie;
    }
}
