package entity;

import okhttp3.Request.Builder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.*;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import static okhttp3.Request.*;


class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private SearchHistory searchHistory = new SearchHistory();

    private Watchlist watchlist = new Watchlist();

    private Map<String, Watchlist> sharedWatchlist = new HashMap<>();

    /**
     * Requires: password is valid.
     *
     * @param name
     * @param password
     * @param searchHistory
     */
    CommonUser(String name, String password, LocalDateTime creationTime, SearchHistory searchHistory, Watchlist watchlist) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.searchHistory = searchHistory;
        this.watchlist = watchlist;
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

//    @Override
//    public List<Movie> addMovies(String keyword) {
//        List<Movie> recommendedMovies = new ArrayList<>();
//
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_keywords=" + keyword)
//                .get()
//                .addHeader("accept", "application/json")
//                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2N2QyZjdhZTQwOTFkMWE4M2Q3YjVjOGJjODZmNzk0MSIsInN1YiI6IjY1MTYzMjZjOTNiZDY5MDBlMTJjY2JmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hMXkt0ZzhfZiVnpZGUDO8oA4nTiWkmaWBhRQNbTgpfg")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            if (response.code() == 200) {
//                JSONObject responseBody = new JSONObject(response.body().string());
//                JSONArray movies = responseBody.getJSONArray("results");
//                for (int i = 0; i < movies.length(); i ++) {
//                    JSONObject movieJson = movies.getJSONObject(i);
//                    String movieTitle = movieJson.getString("title");
//
//                    // Create a Movie object and add it to the recommendedMovies list
//                    Movie movie = new Movie(movieTitle);
//                    recommendedMovies.add(movie);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        // Add the retrieved movies to the search history
//        for (Movie movie: recommendedMovies) {
//            searchHistory.addToSearchHistory(movie);
//        }
//
//        return recommendedMovies;
//    }

    @Override
    public List<Movie> getSearchHistory() {
        return searchHistory.getSearchHistory();
    }

    @Override
    public List<Movie> getWatchlist() {
        return watchlist.getWatchlist();
    }

    @Override
    public void setSharedWatchlist(String userName, Watchlist watchlist) {
        sharedWatchlist.put(userName, watchlist);
    }
}

