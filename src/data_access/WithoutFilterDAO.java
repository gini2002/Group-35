package data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.RecommendMovieWithoutFilter.WithoutFilterDataAccessInterface;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class WithoutFilterDAO implements WithoutFilterDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new HashMap<>();
    private final Map<String, List<Integer>> usernameToWatchlist = new HashMap<>();

    public List<String> getKeywordsForMovie(int movieId) {
        List<String> keywords = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + movieId + "/keywords"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2N2QyZjdhZTQwOTFkMWE4M2Q3YjVjOGJjODZmNzk0MSIsInN1YiI6IjY1MTYzMjZjOTNiZDY5MDBlMTJjY2JmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hMXkt0ZzhfZiVnpZGUDO8oA4nTiWkmaWBhRQNbTgpfg")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JSONObject jsonObject = new JSONObject(responseBody);
                JSONArray keywordsArray = jsonObject.getJSONArray("keywords");

                for (int i = 0; i < keywordsArray.length(); i++) {
                    JSONObject keywordObject = keywordsArray.getJSONObject(i);
                    String keyword = keywordObject.getString("name");
                    keywords.add(keyword);
                }
            } else {
                System.out.println("API Call Failed: HTTP Status Code " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Exception during API Call: " + e.getMessage());
            e.printStackTrace();
        }

        return keywords;
    }

    public WithoutFilterDAO(String csvPath) throws FileNotFoundException, NoDataException {
        csvFile = new File(csvPath);

        headers.put("username", 1);
        headers.put("watchlist", 5);

        if (csvFile.length() == 0) {
            throw new NoDataException("No data to provide movie recommendation");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = col[headers.get("username")];
                    String[] watchlistStr = col[headers.get("watchlist")].split("#");
                    List<Integer> watchlist = new ArrayList<>();
                    for (String id : watchlistStr) {
                        watchlist.add(Integer.valueOf(id.trim()));
                    }
                    usernameToWatchlist.put(username, watchlist);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error reading CSV file", e);
            }
        }
    }

    public List<Integer> getWatchlistMovies(String username) {
        return usernameToWatchlist.getOrDefault(username, Collections.emptyList());
    }

    // Custom exception class
    public static class NoDataException extends Exception {
        public NoDataException(String message) {
            super(message);
        }
    }
}
