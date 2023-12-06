package use_case.GetDetailMovie;
/**
 * The GetDetailMovieInputData class represents the input data for the get detail of movie use case.
 * It encapsulates the name of user that is currently logged in and the id of movie that is aimed to get detail for.
 */
public class GetDetailMovieInputData {
     /** The id of movie that is aimed to get detail for. */
    private int id;
    /** The name of user that is currently logged in. */
    private String loggedinusername;

    /**
     * Constructs a GetDetailMovieInputData object with certain movie id and the name of user that is logged in.
     * @param id The id of movie that is aimed to get detail for.
     * @param loggedinusername The name of user that is currently logged in.
     */
    public GetDetailMovieInputData(int id, String loggedinusername) {

        this.id = id;
        this.loggedinusername = loggedinusername;
    }

    /**
     * Retrieves the id of movie that is aimed to get detail for.
     * @return The id of movie that is aimed to get detail for.
     */
    public int getId(){return id;}

    /**
     * Retrieves the name of user that is currently logged in.
     * @return The name of user that is currently logged in.
     */
    public String getLoggedinusername() {return loggedinusername;}
}
