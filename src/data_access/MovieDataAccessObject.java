package data_access;

import entity.Movie;
import entity.UserFactory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import use_case.MovieSearchByKeyword.SearchByNameDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides data access methods for searching movies by keyword using
 * The Movie Database (TMDb) API.
 */
public class MovieDataAccessObject implements SearchByNameDataAccessInterface {
    private List<Movie> movieRecommendations;
    private List<Movie> searchList;

    private UserFactory userFactory;

    /**
     * Constructs a MovieDataAccessObject with the specified keyword and UserFactory.
     *
     * @param keyword      The keyword for movie search.
     * @param userFactory  The UserFactory to be used for movie recommendations.
     */
    public MovieDataAccessObject(String keyword, UserFactory userFactory) {
        this.userFactory = userFactory;
//        this.movieRecommendations = getRecommendedMovies(keyword);

    }
    /**
     * Retrieves recommended movies based on the provided keyword.
     *
     * @param keyword The keyword for movie search.
     * @return A list of recommended movies.
     */
    @Override
    public List<Movie> getRecommendedMovies(String keyword) {
        String id = getKeywordID(keyword);
        return fetchMovies(id);
    }

    /**
     * Retrieves the ID associated with the provided keyword from The Movie Database (TMDb) API.
     *
     * @param keyword The keyword for movie search.
     * @return The ID associated with the keyword.
     */
    public String getKeywordID(String keyword) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.themoviedb.org/3/search/keyword")
                .newBuilder()
                .addQueryParameter("query", keyword)
                .addQueryParameter("page", "1");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2N2QyZjdhZTQwOTFkMWE4M2Q3YjVjOGJjODZmNzk0MSIsInN1YiI6IjY1MTYzMjZjOTNiZDY5MDBlMTJjY2JmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hMXkt0ZzhfZiVnpZGUDO8oA4nTiWkmaWBhRQNbTgpfg")
                .build();
        String id = null;
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                String responseBodyString = response.body().string();
                JSONObject responseBody = new JSONObject(responseBodyString);
                JSONArray results = responseBody.getJSONArray("results");
                JSONObject idJSON = results.getJSONObject(0);
                id = String.valueOf(idJSON.getInt("id"));

            }
        } catch (IOException e) {
            System.out.println("Exception during API Call: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return id;
    }

    /**
     * Fetches movies based on the provided ID from The Movie Database (TMDb) API.
     *
     * @param id The ID associated with the keyword.
     * @return A list of movies fetched from the API.
     */
    public List<Movie> fetchMovies(String id) {
        List<Movie> recommendedMovies = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_keywords=" + id)
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
                for (int i = 0; i < movies.length(); i++) {
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
