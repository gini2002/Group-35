package data_access;

import entity.Movie;
import entity.UserFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.MovieSearchByKeyword.SearchByNameDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDataAccessObject implements SearchByNameDataAccessInterface {
    //    private Map<String, List<Movie>> movieRecommendations;
    private List<Movie> movieRecommendations;

    private UserFactory userFactory;

    public MovieDataAccessObject(String keyword, UserFactory userFactory) {
        this.userFactory = userFactory;
//        this.movieRecommendations = getRecommendedMovies(keyword);

    }

    @Override
    public List<Movie> getRecommendedMovies(String keyword) {
        return fetchMovies(keyword);
    }

    //    @Override
    public List<Movie> fetchMovies(String keyword) {
        List<Movie> recommendedMovies = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_keywords=" + keyword)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2N2QyZjdhZTQwOTFkMWE4M2Q3YjVjOGJjODZmNzk0MSIsInN1YiI6IjY1MTYzMjZjOTNiZDY5MDBlMTJjY2JmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hMXkt0ZzhfZiVnpZGUDO8oA4nTiWkmaWBhRQNbTgpfg")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                String responseBodyString = response.body().string();
                System.out.println("API Call Successful!");
                System.out.println("Response Body: " + responseBodyString);
                JSONObject responseBody = new JSONObject(responseBodyString);
                JSONArray movies = responseBody.getJSONArray("results");
                for (int i = 0; i < movies.length(); i ++) {
                    JSONObject movieJson = movies.getJSONObject(i);
                    String movieTitle = movieJson.getString("title");
                    int movieId = movieJson.getInt("id");

                    // Create a Movie object and add it to the recommendedMovies list
                    Movie movie = new Movie(movieTitle, movieId);
                    recommendedMovies.add(movie);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception during API Call: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return recommendedMovies;
    }


}
