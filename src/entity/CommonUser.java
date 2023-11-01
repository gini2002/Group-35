package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import okhttp3.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private SearchHistory searchHistory = new SearchHistory();

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime, SearchHistory searchHistory) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.searchHistory = searchHistory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public List<Movie> addMovies(String keyword) {
        // Perform an API call to get recommended movies based on the keyword.
        // Simulate the API call here.
        List<Movie> recommendedMovies = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String apiKey = "67d2f7ae4091d1a83d7b5c8bc86f7941";
        String baseUrl = "https://api.themoviedb.org/3/search/movie";
        String queryString = "?api_key=" + apiKey + "&query=" + keyword;

        // Build the URL
        HttpUrl url = HttpUrl.parse(baseUrl + queryString).newBuilder().build();

        // Create an HTTP request
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Parse the JSON response
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);

                // Extract movie data from the JSON response
                if (jsonObject.has("results")) {
                    JSONArray results = jsonObject.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movieJson = results.getJSONObject(i);
                        String movieName = movieJson.getString("title");

                        // Create a Movie object and add it to the list
                        Movie movie = new Movie(movieName);
                        recommendedMovies.add(movie);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the retrieved movies to the search history
        for (Movie movie: recommendedMovies) {
            searchHistory.addToSearchHistory(movie);
        }

        return recommendedMovies;
    }

    @Override
    public List<Movie> getSearchHistory() {
        return searchHistory.getSearchHistory();
    }
}

