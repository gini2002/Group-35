package use_case.GetDetailMovie;

import java.time.LocalDate;
import java.util.List;
/**
 * The GetDetailMovieOutputData class represents the output data for the get detail of movie use case,
 * including title, overview, genre, release date, movie id, poster path of certain movie and name of user that is currently logged in.
 */
public class GetDetailMovieOutputData {
    /** The title of the movie. */
    private final String title;
    /** The overview of the movie. */
    private final String overview;
    /** The genre of the movie. */
    private final List<String> genre;
    /** The path of the poster of the movie. */
    private final String poster_path;
    /** The release date of the movie. */
    private final LocalDate release_date;
    /** The name of user that is currently logged in. */
    private final String loggedinusername;
    /** The id of the movie. */
    private final int id;

    /**
     * Constructs a GetDetailMovieOutputData object with the title, overview, genre, release date,
     * movie id, poster path of certain movie and name of user that is currently logged in.
     *
     * @param title The title of the movie.
     * @param genre The genre of the movie.
     * @param id The id of the movie.
     * @param loggedinusername The name of user that is currently logged in.
     * @param overview The overview of the movie.
     * @param poster_path The path of the poster of the movie.
     * @param release_date The release date of the movie.
     */
    public GetDetailMovieOutputData(String title, String overview,
                                    List<String> genre,
                                    String poster_path,
                                    int id,
                                    String loggedinusername,
                                    LocalDate release_date){
        this.genre = genre;
        this.overview = overview;
        this.poster_path = poster_path;
        this.title = title;
        this.id = id;
        this.loggedinusername = loggedinusername;
        this.release_date = release_date;
    }
    /**
     * Retrieves the title of the movie we get for certain movie.
     *
     * @return The title of the movie.
     */
    public String getTitle(){
        return title;
    }
    /**
     * Retrieves the overview of the movie we get for certain movie.
     *
     * @return The overview of the movie.
     */
    public String getOverview(){
        return overview;
    }
    /**
     * Retrieves the genre of the movie we get for certain movie.
     *
     * @return The genre of the movie.
     */
    public List<String> getGenre(){return genre;}
    /**
     * Retrieves The path of the poster of the movie.
     *
     * @return The path of the poster of the movie.
     */
    public String getPoster_path() {return poster_path;}
    /**
     * Retrieves the id of the movie we get for certain movie.
     *
     * @return The id of the movie.
     */
    public int getId() {return id;}
    /**
     * Retrieves the name of user that is currently logged in.
     *
     * @return The name of user that is currently logged in.
     */
    public String getLoggedinusername() {return loggedinusername;}
    /**
     * Retrieves the release date of the movie we get for certain movie.
     *
     * @return The release date of the movie.
     */
    public LocalDate getRelease_date() {return release_date;}
}
