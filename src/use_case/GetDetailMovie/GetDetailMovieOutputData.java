package use_case.GetDetailMovie;

import entity.Movie;

import java.util.List;

public class GetDetailMovieOutputData {
    private final String title;
    private final String overview;
    private final List<String> genre;
    private final String poster_path;

    public GetDetailMovieOutputData(String title, String overview,
                                    List<String> genre,
                                    String poster_path){
        this.genre = genre;
        this.overview = overview;
        this.poster_path = poster_path;
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public List<String> getGenre(){return genre;}

    public String getPoster_path() {return poster_path;}
}
