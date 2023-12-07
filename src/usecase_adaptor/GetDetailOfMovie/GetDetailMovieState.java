package usecase_adaptor.GetDetailOfMovie;

import java.time.LocalDate;
import java.util.List;

public class GetDetailMovieState {
    /** title of the movie. */
    private String title;
    /** overview of the movie. */
    private String overview;
    /** genre of the movie. */
    private List<String> genre;
    /** poster paths of the movie. */
    private String poster_path;
    /** error message when error occur */
    private String error;
    /** The user that is logged in currently. */
    private String loggedinusername;
    /** release date of the movie. */
    private LocalDate releaseDate;
    /** id of the movie. */
    private int id;
    /**
     * Constructs a new instance of GetDetailMovieState with the error, genre, title, overview,
     * poster path, movie id, release date and the username of user that is logged in currently of the input state
     * @param copy The state with old values of the error, genre, title, overview,
     * poster path, movie id,  release date and the username of user that is logged in currently
     */
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
    /**
     * Constructs a new instance of GetDetailMovieState.
     * Initializes the state with default values for error, genre, title, overview,
     * poster path, movie id, release date and the username of user that is logged in currently
     */
    public GetDetailMovieState(){}
    /**
     * Sets the title of the movie.
     * @param title The title of the movie.
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * Sets the overview of the movie.
     * @param overview The overview of the movie.
     */
    public void setOverview(String overview){
        this.overview = overview;
    }

    /**
     * Sets the genre of the movie.
     * @param genre The genre of the movie.
     */
    public void setGenre(List<String> genre){
        this.genre = genre;
    }
    /**
     * Sets the path of the poster of the movie.
     * @param poster_path The path of the poster of the movie.
     */
    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}
    /**
     * Sets the id of the movie.
     * @param id The id of the movie.
     */
    public void setId(int id) {this.id = id;}
    /**
     * Sets the date of the movie being released.
     * @param releaseDate The date of the movie being released.
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    /**
     * Retrieves the date of the movie is released.
     * @return the date of the movie is released.
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    /**
     * Sets the name of the user that is currently logged in.
     * @param loggedinusername The name of the user that is currently logged in.
     */
    public void setLoggedinusername(String loggedinusername) {
        this.loggedinusername = loggedinusername;
    }
    /**
     * Retrieves the title of the movie.
     * @return The title of the movie.
     */
    public String getTitle(){
        return title;
    }
    /**
     * Retrieves the current keyword input value.
     * @return The current keyword input value.
     */
    public String getOverview(){
        return overview;
    }
    /**
     * Retrieves the genre of the movie.
     * @return The genre of the movie.
     */
    public List<String> getGenre(){return genre;}
    /**
     * Retrieves the name of user that is currently logged in.
     * @return the name of user that is currently logged in.
     */
    public String getLoggedinusername(){return loggedinusername;}
    /**
     * Retrieves the poster url of the movie.
     * @return The poster url of the movie.
     */
    public String getPoster_path() {return poster_path;}
    /**
     * Retrieves the id of the movie.
     * @return The id of the movie.
     */
    public int getId() {return id;}
    /**
     * Retrieves the error message when error occurs.
     * @return the error message when error occurs.
     */
    public String getError() {
        return error;
    }
    /**
     * Sets the error message when error occurs.
     * @param error the error message when error occurs.
     */
    public void setError(String error){
        this.error = error;
    }
}
