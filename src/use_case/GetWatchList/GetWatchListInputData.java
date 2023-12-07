package use_case.GetWatchList;
/**
 * The GetWatchListInputData class represents the input data for the get watchlist use case.
 * It encapsulates the name of user that is currently logged in.
 */
public class GetWatchListInputData {
    /** The name of user that is currently logged in. */
    private final String name;
    /**
     * Constructs a GetWatchListInputData object with the name of user that is currently logged in.
     * @param name The name of user that is currently logged in.
     */
    public GetWatchListInputData(String name){
        this.name = name;
    }
    /**
     * Retrieves the name of user that is currently logged in.
     * @return The name of user that is currently logged in.
     */
    public String getName(){
        return name;
    }

}
