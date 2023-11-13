package data_access;

import entity.Movie;
import use_case.SearchByNameDataAccessInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDataAccessObject implements SearchByNameDataAccessInterface {
    private Map<String, List<Movie>> movieRecommendations;

    public MovieDataAccessObject() {
        // Initialize the movieRecommendations data structure and add some sample data.
        movieRecommendations = new HashMap<>();

        // For testing purposes, add some sample movie recommendations based on keywords.
        // You can replace this with real data retrieval logic later.
        movieRecommendations.put("action", createSampleActionMovies());
        movieRecommendations.put("comedy", createSampleComedyMovies());
    }

    @Override
    public List<Movie> getRecommendedMovies(String keyword) {
        // Simulate retrieving recommended movies based on the keyword.
        // In a real implementation, you would fetch data from a database or API.
        return movieRecommendations.getOrDefault(keyword, new ArrayList<>());
    }

    // Helper method to create sample action movies.
    private List<Movie> createSampleActionMovies() {
        List<Movie> actionMovies = new ArrayList<>();
        actionMovies.add(new Movie("Die Hard"));
        actionMovies.add(new Movie("Mad Max: Fury Road"));
        actionMovies.add(new Movie("John Wick"));
        return actionMovies;
    }

    // Helper method to create sample comedy movies.
    private List<Movie> createSampleComedyMovies() {
        List<Movie> comedyMovies = new ArrayList<>();
        comedyMovies.add(new Movie("Superbad"));
        comedyMovies.add(new Movie("The Hangover"));
        comedyMovies.add(new Movie("Anchorman"));
        return comedyMovies;
    }
}

//public class MovieDataAccessObject implements MovieDataAccessInterface {
//
//    private static final String BASE_API_URL = "https://api.themoviedb.org/3";
//    private static final String AUTH_TOKEN = "Bearer 67d2f7ae4091d1a83d7b5c8bc86f7941";
//
//    @Override
//    public List<Movie> searchMoviesByKeyword(String keyword) {
//        try {
//            // Construct the full API URL. Modify as per the actual endpoint for keyword search
//            String fullApiUrl = BASE_API_URL + "/search/movie?query=" + keyword;
//
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(fullApiUrl))
//                    .header("accept", "application/json")
//                    .header("Authorization", AUTH_TOKEN)
//                    .method("GET", HttpRequest.BodyPublishers.noBody())
//                    .build();
//
//            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//            return parseMoviesFromResponse(response.body());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of(); // Return an empty list on exception
//        }
//    }
//
//    @Override
//    public void addToSearchHistory(String userId, String keyword) {
//        // This implementation depends on how you want to handle search history.
//        // If you're saving it to a local database, implement that here.
//        // If the API provides an endpoint for saving search history, make an appropriate API call here.
//    }
//
//    private List<Movie> parseMoviesFromResponse(String response) {
//        // ... (same as before)
//    }
//}
