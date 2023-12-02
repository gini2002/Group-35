package data_access;

import entity.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.GetDetailMovie.GetDetailMovieDataAccessInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * This class provides data access methods for get detail of a certain movie using
 * The Movie Database (TMDb) API.
 */
public class MovieDetailAccessAPI implements GetDetailMovieDataAccessInterface {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }


    /**
     * Get the movie with a certain id (movie includes detailed information).
     *
     * @param id The id of the movie that we need get detail for
     * @return The movies with aimed id and
     * other information including title, rating, release date and genres.
     */
    @Override
    public Movie getdetailMovie(int id) {
        String movie_id = String.valueOf(id);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://api.themoviedb.org/3/movie/%s?language=en-US", movie_id))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTM1NDRjZTMxNTEyYjhlZGMzOWFlYWQyMTdiZWFlZCIsInN1YiI6IjY1MTZlZjJmYzUwYWQyMDBjOTFhNjYwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d5F2KzF4gOHTdMcv3AZzazTgKTGv--FzILbQvLVG9EI")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                String title = responseBody.getString("title");
                Double rating = responseBody.getDouble("vote_average");
                String release_date = responseBody.getString("release_date");
                String poster_path = responseBody.getString("poster_path");
                String overview = responseBody.getString("overview");
                JSONArray genres = responseBody.getJSONArray("genres");
                int id2 = responseBody.getInt("id");
                ArrayList<String> genre_lists = new ArrayList<>();
                for (int i = 0; i < genres.length(); i++) {
                    JSONObject genre = genres.getJSONObject(i);
                    genre_lists.add(genre.getString("name"));
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return Movie.builder()
                        .id(id2)
                        .name(title)
                        .poster_path(poster_path)
                        .overview(overview)
                        .rating(rating)
                        .releaseDate(LocalDate.parse(release_date, formatter))
                        .genre(genre_lists)
                        .build();
            } else {
                throw new RuntimeException(responseBody.getString("status_message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Check if the movie with certain movie id exists.
     *
     * @param id The id of the movie that we need get detail for
     * @return true if the movie we are looking for exists. false otherwise
     */
    @Override
    public boolean existsById(int id) {
        String movie_id = String.valueOf(id);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://api.themoviedb.org/3/movie/%s?language=en-US", movie_id))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTM1NDRjZTMxNTEyYjhlZGMzOWFlYWQyMTdiZWFlZCIsInN1YiI6IjY1MTZlZjJmYzUwYWQyMDBjOTFhNjYwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d5F2KzF4gOHTdMcv3AZzazTgKTGv--FzILbQvLVG9EI")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 404) {
                return false;
            } else {return true;}
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    // if the status message is returned as "The resource you requested could not be found."
    // the movie is not found so is not exist by name
}
