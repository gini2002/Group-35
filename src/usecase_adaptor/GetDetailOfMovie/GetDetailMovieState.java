package usecase_adaptor.GetDetailOfMovie;

import java.util.List;

public class GetDetailMovieState {
    private String title;
    private String overview;
    private List<String> genre;
    private List<String> actors;
    private String poster_path;
    private String error;

    public GetDetailMovieState(GetDetailMovieState copy){
        error = copy.error;
        actors = copy.actors;
        genre = copy.genre;
        title = copy.title;
        overview = copy.overview;
        poster_path = copy.poster_path;
    }
    public GetDetailMovieState(){}

    public void setTitle(String title){
        this.title = title;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public void setGenre(List<String> genre){
        this.genre = genre;
    }

    public void setActors(List<String> actors){
        this.actors = actors;
    }

    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public List<String> getGenre(){return genre;}

    public List<String> getActors(){
        return actors;
    }

    public String getPoster_path() {return poster_path;}

    public String getError() {
        return error;
    }
    public void setError(String error){
        this.error = error;
    }
}
