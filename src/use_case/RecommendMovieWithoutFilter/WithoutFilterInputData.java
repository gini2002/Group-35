package use_case.RecommendMovieWithoutFilter;

/**
 * This class represents the input data for the "Recommend Movie Without Filter" use case.
 * It encapsulates the necessary information required to perform the recommendation process,
 * primarily focusing on user-related data. This class can be extended to include additional
 * fields such as user preferences if needed.
 */
public class WithoutFilterInputData {
    private final String username;

    /**
     * Constructs a new WithoutFilterInputData instance with a specified username.
     * The username is used to identify the user's preferences or watchlist in the movie recommendation process.
     *
     * @param username The username of the user for whom the movie recommendations are being generated.
     */
    public WithoutFilterInputData(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username associated with this input data.
     * This username is used in the recommendation process to tailor the results to the specific user.
     *
     * @return The username of the user.
     */

    public String getUsername() {
        return username;
    }
}