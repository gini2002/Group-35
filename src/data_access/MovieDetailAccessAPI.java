package data_access;

import entity.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MovieDetailAccessAPI implements MovieDetailAPI{
    private static final String API_URL = "https://grade-logging-api.chenpan.ca/api/grade";
    // load API_TOKEN from env variable.
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }



    @Override
    public Movie getdetailMovie(int id) {
        String movie_id = String.valueOf(id);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://api.themoviedb.org/3/movie/movie_id=%s?language=en-US", movie_id))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.getInt("status_code") == 200) {
                JSONObject title = responseBody.getJSONObject("title");
                JSONObject rating = responseBody.getJSONObject("vote_average");
                JSONObject release_date = responseBody.getJSONObject("release_date");
                JSONObject poster_path = responseBody.getJSONObject("poster_path");
                JSONObject overview = responseBody.getJSONObject("overview");
                JSONArray genres = responseBody.getJSONArray("genres");
                JSONObject id2 = responseBody.getJSONObject("id");
                ArrayList<String> genre_lists = new ArrayList<>();
                for (int i = 0; i < genres.length(); i++) {
                    JSONObject genre = genres.getJSONObject(i);
                    genre_lists.add(genre.getString("name"));
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return Movie.builder()
                        .id(id2.getInt("id"))
                        .name(title.getString("title"))
                        .poster_path(poster_path.getString("poster_path"))
                        .overview(overview.getString("overview"))
                        .rating(rating.getDouble("rating"))
                        .releaseDate(LocalDate.parse(release_date.getString("release_date"), formatter))
                        .genre(genre_lists)
                        .build();
            } else {
                throw new RuntimeException(responseBody.getString("status_message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existbyname() {
        return false;
    }
    // if the status message is returned as "The resource you requested could not be found."
    // the movie is not found so is not exist by name
}
