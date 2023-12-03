package usecase_adaptor.GetDetailOfMovie;

import java.time.LocalDate;
import java.util.List;

public class GetDetailMovieState {
    private String title;
    private String overview;
    private List<String> genre;
    private String poster_path;
    private String error;
    private String loggedinusername;
    private LocalDate releaseDate;

    private int id;

    public GetDetailMovieState(GetDetailMovieState copy){
        error = copy.error;
        genre = copy.genre;
        title = copy.title;
        overview = copy.overview;
        poster_path = copy.poster_path;
        id = copy.id;
        releaseDate = copy.releaseDate;
        loggedinusername = copy.loggedinusername;
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

    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}

    public void setId(int id) {this.id = id;}

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setLoggedinusername(String loggedinusername) {
        this.loggedinusername = loggedinusername;
    }

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public List<String> getGenre(){return genre;}

    public String getLoggedinusername(){return loggedinusername;}

    public String getPoster_path() {return poster_path;}

    public int getId() {return id;}

    public String getError() {
        return error;
    }
    public void setError(String error){
        this.error = error;
    }
}
