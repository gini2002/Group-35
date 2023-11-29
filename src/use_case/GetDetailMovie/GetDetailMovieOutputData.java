package use_case.GetDetailMovie;

import java.util.List;

public class GetDetailMovieOutputData {
    private final String title;
    private final String overview;
    private final List<String> genre;
    private final String poster_path;
    private final String loggedinusername;

    private final int id;

    public GetDetailMovieOutputData(String title, String overview,
                                    List<String> genre,
                                    String poster_path,
                                    int id,
                                    String loggedinusername){
        this.genre = genre;
        this.overview = overview;
        this.poster_path = poster_path;
        this.title = title;
        this.id = id;
        this.loggedinusername = loggedinusername;
    }

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public List<String> getGenre(){return genre;}

    public String getPoster_path() {return poster_path;}

    public int getId() {return id;}

    public String getLoggedinusername() {return loggedinusername;}
}
