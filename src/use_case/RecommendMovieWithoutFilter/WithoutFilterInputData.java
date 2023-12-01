package use_case.RecommendMovieWithoutFilter;


public class WithoutFilterInputData {
    private final String username; // Identifier for the user's watchlist, if used separately

    // Additional fields can be added as needed, for example, user preferences

    // Constructor
    public WithoutFilterInputData(String username) {
        this.username = username;
    }

    // Getters for the fields

    public String getUsername() {
        return username;
    }
}