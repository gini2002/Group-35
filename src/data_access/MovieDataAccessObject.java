package data_access;

import entity.Movie;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MovieDataAccessObject implements MovieDataAccessInterface {

    private static final String BASE_API_URL = "https://api.themoviedb.org/3";
    private static final String AUTH_TOKEN = "Bearer 67d2f7ae4091d1a83d7b5c8bc86f7941";

    @Override
    public List<Movie> searchMoviesByKeyword(String keyword) {
        try {
            // Construct the full API URL. Modify as per the actual endpoint for keyword search
            String fullApiUrl = BASE_API_URL + "/search/movie?query=" + keyword;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullApiUrl))
                    .header("accept", "application/json")
                    .header("Authorization", AUTH_TOKEN)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return parseMoviesFromResponse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Return an empty list on exception
        }
    }

    public List<Movie> getMoviesByGenre(String genre) {
        try {
            // Modify as per the actual endpoint for fetching movies by genre
            String fullApiUrl = BASE_API_URL + "/discover/movie?with_genres=" + genre;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullApiUrl))
                    .header("accept", "application/json")
                    .header("Authorization", AUTH_TOKEN)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return parseMoviesFromResponse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Return an empty list on exception
        }
    }

    @Override
    public void addToSearchHistory(String userId, String keyword) {
        // This implementation depends on how you want to handle search history.
        // If you're saving it to a local database, implement that here.
        // If the API provides an endpoint for saving search history, make an appropriate API call here.
    }

    private List<Movie> parseMoviesFromResponse(String response) {
        // ... (same as before)
    }
}
